package com.example.lib_neuq_mvvm.base.view;

import com.example.lib_neuq_mvvm.base.viewmodel.MultiStatusViewModel;
import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

/**
 * Time:2020/2/3 12:01
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class MultiStatusActivity<D extends ViewDataBinding, VM extends MultiStatusViewModel> extends BaseNetWorkActivity<D, VM> {

    @Override
    protected void observeNetWorkStatus() {
        super.observeNetWorkStatus();

        Iterator<Map.Entry<String, UnPeekLiveData<NetWorkStatus>>> iteratorStatus = mViewModel.getStatusMap().entrySet().iterator();
        while (iteratorStatus.hasNext()) {
            Map.Entry<String, UnPeekLiveData<NetWorkStatus>> entry = iteratorStatus.next();
            entry.getValue().observe(this, netWorkStatus -> {
                onMultiStatusChange(entry.getKey(), netWorkStatus);
            });
        }

        Iterator<Map.Entry<String, UnPeekLiveData<NetWorkException>>> iteratorError = mViewModel.getStatusMap().entrySet().iterator();
        while (iteratorError.hasNext()) {
            Map.Entry<String, UnPeekLiveData<NetWorkException>> entry = iteratorError.next();
            entry.getValue().observe(this, error -> {
                onMultiErrorChange(entry.getKey(), error);
            });
        }

    }

    protected void onMultiErrorChange(String key, NetWorkException error) {
        
    }

    protected void onMultiStatusChange(String key, NetWorkStatus status) {

    }
}
