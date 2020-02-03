package com.example.lib_neuq_mvvm.base.viewmodel;

import com.example.lib_neuq_mvvm.base.model.MultiStatusRepository;
import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import java.util.HashMap;

/**
 * Time:2020/2/3 11:13
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class MultiStatusViewModel<R extends MultiStatusRepository> extends BaseNetWorkViewModel<R> {

    protected HashMap<String, UnPeekLiveData<NetWorkStatus>> mStatusMap;
    protected HashMap<String, UnPeekLiveData<NetWorkException>> mErrorMap;

    public MultiStatusViewModel(R repository) {
        super(repository);
        mStatusMap = new HashMap<>();
        mErrorMap = new HashMap<>();
        loadData();
        registerStateMap();
        repository.setExceptionMap(mErrorMap);
        repository.setStatusMap(mStatusMap);
    }

    protected abstract void loadData();

    public HashMap<String, UnPeekLiveData<NetWorkStatus>> getStatusMap() {
        return mStatusMap;
    }

    public HashMap<String, UnPeekLiveData<NetWorkException>> getErrorMap() {
        return mErrorMap;
    }

    protected abstract void registerStateMap();

}
