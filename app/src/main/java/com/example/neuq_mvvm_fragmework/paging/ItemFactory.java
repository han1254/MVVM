package com.example.neuq_mvvm_fragmework.paging;

import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;
import com.example.neuq_mvvm_fragmework.model.Repo;
import com.example.neuq_mvvm_fragmework.model.RequestModel;
import com.example.neuq_mvvm_fragmework.paging.itemkey.ItemKeySource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Time:2020/1/28 22:00
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class ItemFactory extends DataSource.Factory<Long, Repo> {

    private MutableLiveData<RequestModel> requestModelLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Repo>> repoLiveData = new MutableLiveData<>();
    private MutableLiveData<NetWorkStatus> statusLiveData;
    private MutableLiveData<NetWorkException> error;
    public ItemFactory(MutableLiveData<RequestModel> requestModelLiveData,
                       MutableLiveData<List<Repo>> repoLiveData,
                       MutableLiveData<NetWorkStatus> statusLiveData,
                       MutableLiveData<NetWorkException> error) {
        this.requestModelLiveData = requestModelLiveData;
        this.repoLiveData = repoLiveData;
        this.statusLiveData = statusLiveData;
        this.error = error;
    }

    @NonNull
    @Override
    public DataSource<Long, Repo> create() {
        return new ItemKeySource(requestModelLiveData, repoLiveData, statusLiveData, error);
    }
}
