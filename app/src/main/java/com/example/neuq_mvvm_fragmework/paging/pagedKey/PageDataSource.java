package com.example.neuq_mvvm_fragmework.paging.pagedKey;

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
import androidx.paging.PageKeyedDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time:2020/1/28 10:53
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class PageDataSource extends PageKeyedDataSource<Integer, Repo> {

    private MutableLiveData<RequestModel> queryData = new MutableLiveData<>();
    private MutableLiveData<List<Repo>> resultData = new MutableLiveData<>();
    private MutableLiveData<NetWorkStatus> statusLiveData = new MutableLiveData<>();

    public PageDataSource(MutableLiveData<RequestModel> queryData, MutableLiveData<List<Repo>> resultData, MutableLiveData<NetWorkStatus> statusLiveData) {
        this.queryData = queryData;
        this.resultData = resultData;
        this.statusLiveData = statusLiveData;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repo> callback) {
        Repository.getRepos(queryData.getValue().getQuery() != null ?
                        queryData.getValue().getQuery() : "",
                queryData.getValue().getPage(),
                queryData.getValue().getCount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(10)
                .subscribe(new DefaultObserver<RepoSearchResponse>(new NetWorkExceptionController() {
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
                    public void onSubscribe(Disposable d) {
                        statusLiveData.postValue(NetWorkStatus.LOADING);
                    }

                    @Override
                    public void onSuccess(RepoSearchResponse repoSearchResponse) {
                        statusLiveData.setValue(NetWorkStatus.DONE);
                        resultData.postValue(repoSearchResponse.getItems());
                        callback.onResult(repoSearchResponse.getItems(),
                                null,
                               1);
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
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {

        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
        Repository.getRepos(queryData.getValue().getQuery() != null ?
                        queryData.getValue().getQuery() : "",
                queryData.getValue().getPage(),
                queryData.getValue().getCount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(new DefaultObserver<RepoSearchResponse>(new NetWorkExceptionController() {
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
                    public void onSuccess(RepoSearchResponse repoSearchResponse) {
                        resultData.postValue(repoSearchResponse.getItems());
                        callback.onResult(repoSearchResponse.getItems(),
                        adjacentKey);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        statusLiveData.postValue(NetWorkStatus.LOADING);
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
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {
        Repository.getRepos(queryData.getValue().getQuery() != null ?
                        queryData.getValue().getQuery() : "",
                queryData.getValue().getPage(),
                queryData.getValue().getCount())
                .subscribeOn(Schedulers.io())
                .retry()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<RepoSearchResponse>(new NetWorkExceptionController() {
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
                    public void onSuccess(RepoSearchResponse repoSearchResponse) {
                        resultData.postValue(repoSearchResponse.getItems());
                        callback.onResult(repoSearchResponse.getItems(),
                                params.key + 1);
                    }


                    @Override
                    public void onSubscribe(Disposable d) {
                        statusLiveData.postValue(NetWorkStatus.LOADING);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RequestModel model = queryData.getValue();
        model.setPage(model.getPage() + 1);
        queryData.postValue(model);
    }
}
