package com.example.lib_neuq_mvvm.base.model;
import com.example.lib_neuq_mvvm.livedata.SingleObserverLiveData;
import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Time:2020/1/31 9:59
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface IRepository extends LifecycleObserver {

    UnPeekLiveData<NetWorkStatus> getStatus();

    void setStatus(NetWorkStatus netWorkStatus);

    UnPeekLiveData<NetWorkException> getError();

    void setError(NetWorkException e);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event);

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause();

}
