package com.example.lib_neuq_mvvm.base.viewmodel;
import com.example.lib_neuq_mvvm.base.model.BaseRepository;
import com.example.lib_neuq_mvvm.livedata.SingleObserverLiveData;
import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

/**
 * Time:2020/1/23 7:37
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class BaseNetWorkViewModel<R extends BaseRepository> extends BaseViewModel {

    protected R repository;
    protected Boolean isAutoShowNetWorkStatus;

    protected UnPeekLiveData<NetWorkStatus> mStatus;
    protected UnPeekLiveData<NetWorkException> mError;

    public BaseNetWorkViewModel(R repository) {
        this.repository = repository;
        mError = repository.getError() == null ? new UnPeekLiveData<>() : repository.getError();
        mStatus = repository.getStatus() == null ? new UnPeekLiveData<>() : repository.getStatus();
    }

    public Boolean getIsAutoNetWorkStatus() {
        return isAutoShowNetWorkStatus;
    }

    public UnPeekLiveData<NetWorkStatus> getNetWorkStatusLiveData() {
        return mStatus;
    }

    public UnPeekLiveData<NetWorkException> getError() {
        return mError;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
