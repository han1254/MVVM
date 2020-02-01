package com.example.neuq_mvvm_fragmework.paging.itemkey;

import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.rx.DefaultObserver;
import com.example.lib_neuq_mvvm.network.rx.NetWorkExceptionController;
import com.example.neuq_mvvm_fragmework.model.Repo;
import com.example.neuq_mvvm_fragmework.RepoSearchResponse;
import com.example.neuq_mvvm_fragmework.model.Repository;
import com.example.neuq_mvvm_fragmework.model.RequestModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.ItemKeyedDataSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time:2020/1/28 15:44
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class ItemKeySource extends ItemKeyedDataSource<Long, Repo> {

    private MutableLiveData<RequestModel> queryData = new MutableLiveData<>();
    private MutableLiveData<List<Repo>> resultData = new MutableLiveData<>();
    private MutableLiveData<NetWorkStatus> statusLiveData = new MutableLiveData<>();

    public ItemKeySource(MutableLiveData<RequestModel> queryData, MutableLiveData<List<Repo>> resultData, MutableLiveData<NetWorkStatus> statusLiveData) {
        this.queryData = queryData;
        this.resultData = resultData;
        this.statusLiveData = statusLiveData;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Repo> callback) {
        Repository.getRepos(queryData.getValue().getQuery() != null ?
                        queryData.getValue().getQuery() : "",
                queryData.getValue().getPage(),
                queryData.getValue().getCount())
                .subscribeOn(Schedulers.io())
                .retry(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RepoSearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        statusLiveData.postValue(NetWorkStatus.LOADING);
                    }

                    @Override
                    public void onNext(RepoSearchResponse repoSearchResponse) {
                        statusLiveData.setValue(NetWorkStatus.DONE);
                        resultData.postValue(repoSearchResponse.getItems());
                        callback.onResult(repoSearchResponse.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        RequestModel model = queryData.getValue();
        model.setPage(model.getPage() + 1);
        queryData.postValue(model);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Repo> callback) {
        Repository.getRepos(queryData.getValue().getQuery() != null ?
                        queryData.getValue().getQuery() : "",
                queryData.getValue().getPage(),
                queryData.getValue().getCount())
                .subscribeOn(Schedulers.io())
                .retry(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<RepoSearchResponse>(new NetWorkExceptionController() {
                    @Override
                    public void badNetWorkError() {
                        statusLiveData.postValue(NetWorkStatus.FAILED);
                    }

                    @Override
                    public void connectError() {
                        statusLiveData.postValue(NetWorkStatus.FAILED);
                    }

                    @Override
                    public void timeOutError() {
                        statusLiveData.postValue(NetWorkStatus.FAILED);
                    }

                    @Override
                    public void parseError() {
                        statusLiveData.postValue(NetWorkStatus.FAILED);
                    }

                    @Override
                    public void unknownError() {
                        statusLiveData.postValue(NetWorkStatus.FAILED);
                    }
                }) {
                    @Override
                    public void onSuccess(RepoSearchResponse repoSearchResponse) {
                        resultData.postValue(repoSearchResponse.getItems());
                        callback.onResult(repoSearchResponse.getItems());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        statusLiveData.postValue(NetWorkStatus.LOADING);
                    }

                    @Override
                    public void onComplete() {
                        statusLiveData.postValue(NetWorkStatus.DONE);
                    }
                });
        RequestModel model = queryData.getValue();
        model.setPage(model.getPage() + 1);
        queryData.postValue(model);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Repo> callback) {

    }

    @NonNull
    @Override
    public Long getKey(@NonNull Repo item) {
        return item.getId();
    }

}
