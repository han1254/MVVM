package com.example.neuq_mvvm_fragmework.model;
import com.example.lib_neuq_mvvm.base.model.MultiStatusRepository;
import com.example.lib_neuq_mvvm.livedata.LiveDataBus;
import com.example.lib_neuq_mvvm.network.base.AppExecutors;
import com.example.lib_neuq_mvvm.network.base.NetWorkSingleResource;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.base.Resource;
import com.example.lib_neuq_mvvm.network.retrofit.GetApiService;
import com.example.lib_neuq_mvvm.network.rx.DefaultObserver;
import com.example.lib_neuq_mvvm.network.rx.NetWorkExceptionController;
import com.example.neuq_mvvm_fragmework.Api;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time:2020/1/31 16:51
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TabTestRepository extends MultiStatusRepository {

    public MutableLiveData<MsgModel> getRemote() {
        MutableLiveData<Resource<String>> liveData = new MutableLiveData<>();

        liveData.setValue(Resource.loading("正在请求"));

       return  (new  NetWorkSingleResource<MsgModel>(new AppExecutors()) {

            @Override
            public LiveData<MsgModel> getRemoteSource() {
                MutableLiveData<MsgModel> liveData1 = new MutableLiveData<>();
                GetApiService.getApiService(Api.class, "https://api.apiopen.top/")
                        .getString(1, 20)
                        .subscribeOn(Schedulers.io())
                        .subscribe(new DefaultObserver<MsgModel>(new NetWorkExceptionController() {
                            @Override
                            public void badNetWorkError() {

                            }

                            @Override
                            public void connectError() {

                            }

                            @Override
                            public void timeOutError() {

                            }

                            @Override
                            public void parseError() {

                            }

                            @Override
                            public void unknownError() {

                            }
                        }) {
                            @Override
                            public void onSuccess(MsgModel msgModel) {
                                liveData1.postValue(msgModel);
                                setMultiStatus("strStatus1", NetWorkStatus.DONE);
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                setMultiStatus("strStatus1", NetWorkStatus.LOADING);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                return liveData1;
            }

            @Override
            public LiveData<MsgModel> getLocalSource() {
                MutableLiveData<MsgModel> t = new MutableLiveData<>();
                t.postValue(new MsgModel(100, "lalalala", "这是内容1"));
                return t;
            }

            @Override
            public void saveData(MsgModel item) {
                LiveDataBus.get()
                        .with("saveData1")
                        .postValue("1获得数据并且储存");
            }

            @Override
            public void dealNetCode(int code, String msg) {
                LiveDataBus.get()
                        .with("getCode1")
                        .postValue(code);
            }

            @Override
            public boolean shouldFetch(MsgModel item) {
                return true;
            }
        }).getResult();

//       return resource.getResult();

    }



    public MutableLiveData<MsgModel> getRemote2(boolean isFetch, MutableLiveData<MsgModel> model) {
        MutableLiveData<Resource<String>> liveData = new MutableLiveData<>();

        liveData.setValue(Resource.loading("正在请求"));

        return  (new  NetWorkSingleResource<MsgModel>(new AppExecutors()) {

            @Override
            public LiveData<MsgModel> getRemoteSource() {
                MutableLiveData<MsgModel> liveData1 = new MutableLiveData<>();
                GetApiService.getApiService(Api.class, "https://api.apiopen.top/")
                        .getString(1, 20)
                        .subscribeOn(Schedulers.io())
                        .subscribe(new DefaultObserver<MsgModel>(new NetWorkExceptionController() {
                            @Override
                            public void badNetWorkError() {

                            }

                            @Override
                            public void connectError() {

                            }

                            @Override
                            public void timeOutError() {

                            }

                            @Override
                            public void parseError() {

                            }

                            @Override
                            public void unknownError() {

                            }
                        }) {
                            @Override
                            public void onSuccess(MsgModel msgModel) {
                                model.postValue(msgModel);
                                setMultiStatus("strStatus2", NetWorkStatus.DONE);
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                setMultiStatus("strStatus2", NetWorkStatus.LOADING);
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                return model;
            }

            @Override
            public LiveData<MsgModel> getLocalSource() {
                MutableLiveData<MsgModel> t = new MutableLiveData<>();
                t.postValue(new MsgModel(100, "lalalala", "这是本地的内容2"));
                return t;
            }

            @Override
            public void saveData(MsgModel item) {
                LiveDataBus.get()
                        .with("saveData2")
                        .postValue("2获得数据并且储存");
            }

            @Override
            public void dealNetCode(int code, String msg) {
                LiveDataBus.get()
                        .with("getCode2")
                        .postValue(code);
            }

            @Override
            public boolean shouldFetch(MsgModel item) {
                return isFetch;
            }
        }).getResult();

//       return resource.getResult();

    }

}
