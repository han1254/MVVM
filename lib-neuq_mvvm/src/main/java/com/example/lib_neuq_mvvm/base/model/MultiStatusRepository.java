package com.example.lib_neuq_mvvm.base.model;

import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import java.io.IOException;
import java.util.HashMap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/2/3 11:15
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class MultiStatusRepository extends BaseRepository {

    HashMap<String, UnPeekLiveData<NetWorkStatus>> mStatusMap;
    HashMap<String, UnPeekLiveData<NetWorkException>> mExceptionMap;

    public MultiStatusRepository(HashMap<String, UnPeekLiveData<NetWorkStatus>> mStatusMap,
                                 HashMap<String, UnPeekLiveData<NetWorkException>> mExceptionMap) {
        this.mStatusMap = mStatusMap;
        this.mExceptionMap = mExceptionMap;
    }

    public MultiStatusRepository() {
        mStatusMap = new HashMap<>();
        mExceptionMap = new HashMap<>();
    }

    public void setStatusMap( HashMap<String, UnPeekLiveData<NetWorkStatus>> mStatusMap) {
        this.mStatusMap = mStatusMap;
    }

    public void setExceptionMap(HashMap<String, UnPeekLiveData<NetWorkException>> mExceptionMap) {
        this.mExceptionMap = mExceptionMap;
    }

    protected void setMultiStatus(String name, NetWorkStatus value) throws NullPointerException {
        if ("".equals(name) || name == null) {
            throw new NullPointerException("status name shouldn't be null");
        }
        value = value == null ? NetWorkStatus.INIT : value;
        if (!mStatusMap.containsKey(name)) {
            UnPeekLiveData<NetWorkStatus> statusValue = new UnPeekLiveData<>();
            statusValue.postValue(value);
            mStatusMap.put(name, statusValue);
        } else {
            mStatusMap.get(name).postValue(value);
        }
    }

    protected void setMultiException(String name, NetWorkException value) throws NullPointerException {
        if ("".equals(name) || name == null) {
            throw new NullPointerException("status name shouldn't be null");
        }

        value = value == null ? NetWorkException.NO_ERROR : value;

        if (!mExceptionMap.containsKey(name)) {
            UnPeekLiveData<NetWorkException> statusValue = new UnPeekLiveData<>();
            statusValue.postValue(value);
            mExceptionMap.put(name, statusValue);
        } else {
            mExceptionMap.get(name).postValue(value);
        }
    }

    public UnPeekLiveData<NetWorkStatus> getMultiSatus(String key)  {

        if ("".equals(key) || key == null) {
            throw new NullPointerException("status name shouldn't be null");
        }

        if (mStatusMap.containsKey(key)) {
            return mStatusMap.get(key);
        } else {
            throw new IllegalArgumentException("mStatusMap doesn't contain the key");
        }
    }

    public UnPeekLiveData<NetWorkException> getMultiException(String key)  {
        if ("".equals(key) || key == null) {
            throw new NullPointerException("status name shouldn't be null");
        }

        if (mExceptionMap.containsKey(key)) {
            return mExceptionMap.get(key);
        } else {
            throw new IllegalArgumentException("mStatusMap doesn't contain the key");
        }
    }
}
