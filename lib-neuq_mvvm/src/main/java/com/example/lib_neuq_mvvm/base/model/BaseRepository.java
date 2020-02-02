package com.example.lib_neuq_mvvm.base.model;
import com.example.lib_neuq_mvvm.livedata.SingleObserverLiveData;
import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Time:2020/1/23 7:38
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BaseRepository implements IRepository {

    private UnPeekLiveData<NetWorkStatus> status;
    private UnPeekLiveData<NetWorkException> error;
    private CompositeDisposable mCompositeDisposable;

    public BaseRepository() {
        status = new UnPeekLiveData<>();
        status.postValue(NetWorkStatus.INIT);
        error = new UnPeekLiveData<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public UnPeekLiveData<NetWorkStatus> getStatus() {
        if (status == null) {
            status = new UnPeekLiveData<>();
            status.postValue(NetWorkStatus.INIT);
        }
        return status;
    }

    @Override
    public void setStatus(NetWorkStatus netWorkStatus) {
        status.postValue(netWorkStatus);
    }

    @Override
    public UnPeekLiveData<NetWorkException> getError() {
        return this.error;
    }

    @Override
    public void setError(NetWorkException e) {
        this.error.postValue(e);
    }


    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        //销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
