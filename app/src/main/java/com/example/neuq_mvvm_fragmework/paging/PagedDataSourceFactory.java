package com.example.neuq_mvvm_fragmework.paging;

import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.neuq_mvvm_fragmework.model.Repo;
import com.example.neuq_mvvm_fragmework.model.RequestModel;
import com.example.neuq_mvvm_fragmework.paging.pagedKey.PageDataSource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Time:2020/1/28 10:53
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class PagedDataSourceFactory extends DataSource.Factory<Integer, Repo> {

    private MutableLiveData<RequestModel> requestModelLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Repo>> repoLiveData = new MutableLiveData<>();
    private MutableLiveData<NetWorkStatus> statusLiveData;
    public PagedDataSourceFactory(MutableLiveData<RequestModel> requestModelLiveData, MutableLiveData<List<Repo>> repoLiveData, MutableLiveData<NetWorkStatus> statusLiveData) {
        this.requestModelLiveData = requestModelLiveData;
        this.repoLiveData = repoLiveData;
        this.statusLiveData = statusLiveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, Repo> create() {
        return new PageDataSource(requestModelLiveData, repoLiveData, statusLiveData);
    }
}
