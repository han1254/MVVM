package com.example.neuq_mvvm_fragmework.model;

import com.example.lib_neuq_mvvm.base.model.BaseRepository;
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
public class TabTestRepository extends BaseRepository {

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
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                setStatus(NetWorkStatus.LOADING);
                            }

                            @Override
                            public void onComplete() {
                                setStatus(NetWorkStatus.DONE);
                            }
                        });

                return liveData1;
            }

            @Override
            public LiveData<MsgModel> getLocalSource() {
                MutableLiveData<MsgModel> t = new MutableLiveData<>();
                t.postValue(new MsgModel(100, "lalalaa", "这是内容"));
                return t;
            }

            @Override
            public void saveData(MsgModel item) {
                LiveDataBus.get()
                        .with("saveData")
                        .postValue("获得数据并且储存");
            }

            @Override
            public void dealNetCode(int code, String msg) {
                LiveDataBus.get()
                        .with("getCode")
                        .postValue(code);
            }

            @Override
            public boolean shouldFetch(MsgModel item) {
                return true;
            }
        }).getResult();

//       return resource.getResult();

    }

}
