package com.example.lib_neuq_mvvm.network.base;

import android.webkit.WebStorage;

import com.example.lib_neuq_mvvm.network.retrofit.BaseResponse;
import com.example.lib_neuq_mvvm.utils.ContextUtil;
import com.example.lib_neuq_mvvm.utils.ToastUtil;
import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

/**
 * Time:2020/1/25 17:04
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */

/**
 * 两种泛型，例如我们在往room里存数据的时候可能存的是(RequestType)List<Integer>，
 * 但是当我们取出时有可能需要的是(OriginType)LiveData<List<Integer>>
 * @param <ResultType>
 * @param <OriginType>
 */
public abstract class NetworkBoundResource<ResultType, OriginType> {

    private AppExecutors appExecutors;
    /**
     * MediatorLiveData是一种特殊的LiveData，它可以添加对多个LiveData的观察，
     * 任何一个LiveData发生变化，就会对其进行通知，例如：
     * class MediatorLiveDataFragment : Fragment() {
     *
     *     private val changeObserver = Observer<String> { value ->
     *         value?.let { txt_fragment.text = it }
     *     }
     *
     *     override fun onAttach(context: Context?) {
     *         super.onAttach(context)
     *         val mediatorLiveData = MediatorLiveData<String>()
     *         mediatorLiveData.addSource(getliveDataA())
     *               { mediatorLiveData.value = "A:$it" }
     *         mediatorLiveData.addSource(getliveDataB())
     *               { mediatorLiveData.value = "B:$it" }
     *         mediatorLiveData.observe(this, changeObserver)
     *     }
     *     // .. some other Fragment specific code ..
     * }
     */
    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.loading(null));
        //request and get data from local db at first
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, resultData -> {
            //remove the observation of the LiveData from local database
            result.removeSource(dbSource);
            if (shouldFetch(resultData)) {
                fetchFromNetWork(dbSource);
            } else {
                result.addSource(dbSource, newData -> {
                    //对LiveData<ResultType>中的数据进行包装后获得Resource，
                    //每当dbSource发生变动时，利用Resource更新result数据
                  setValue(Resource.success(newData));
                });
            }
        });
    }

    private void setValue(Resource<ResultType> success) {
        if (result.getValue() != success) {
            result.setValue(success);
        }
    }


    private void fetchFromNetWork(LiveData<ResultType> dbData) {
        LiveData<BaseResponse<OriginType>> response = createCall();
        result.addSource(dbData, newData -> {
            setValue(Resource.loading(newData));
        });
        result.addSource(response, responseData -> {
            result.removeSource(response);
            result.removeSource(dbData);
            switch (responseData.getCode()) {
                case Config.HTTP_SUCCESS :
                    appExecutors.getDisIO().execute(() -> {
                        saveCallResult(responseData.getContent());
                        appExecutors.getMainThread().execute(() -> {
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            //数据倒灌问题，由于LiveData的mVersion与Observer的lastVersion的问题，导致
                            //同一个LiveData，第二次设置观察者时会获得第一次最后的数据，也就是数据倒灌
                            result.addSource(loadFromDb(), newData -> {
                                setValue(Resource.success(newData));
                            });
                        });
                    });
                    break;
                case Config.DEFAULT_TIMEOUT:
                    ToastUtil.showLong(ContextUtil.getInstance().build().getContext(), "请求超时");
                case Config.REQUEST_ERROR:
                    ToastUtil.showLong(ContextUtil.getInstance().build().getContext(), "请求错误");
                case Config.SEVER_ERROR:
                    ToastUtil.showLong(ContextUtil.getInstance().build().getContext(), "服务器错误");
                    appExecutors.getMainThread().execute(() -> {
                        result.addSource(dbData, newData -> {
                            setValue(Resource.error(responseData.getMessage(), newData));
                        });
                    });
                    break;
            }
        });
    }

    /**
     * save data to the local database
     * @param content
     */
    @MainThread
    protected abstract void saveCallResult(OriginType content);

    /**
     * at first you should request data from local
     * database such as Room.
     * @return
     */
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    /**
     * To decide whether you should request data from
     * remote sever.
     * @param data
     * @return
     */
    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    /**
     * get the remote data from server
     * @return
     */
    @MainThread
    protected abstract LiveData<BaseResponse<OriginType>> createCall();

    public LiveData<Resource<ResultType>> getResultData() {
        return result;
    }
}
