package com.example.lib_neuq_mvvm.base.view;

import android.accounts.NetworkErrorException;
import android.os.Bundle;

import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

/**
 * Time:2020/1/31 10:55
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class BaseNetWorkActivity<D extends ViewDataBinding, VM extends BaseNetWorkViewModel> extends BaseActivity<D, VM> implements INetWorkView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeNetWorkStatus();
    }

    protected void observeNetWorkStatus() {

        mViewModel.getNetWorkStatusLiveData().observe(this, status -> {
            switch (((NetWorkStatus)status)) {
                case LOADING:
                    onNetLoading();
                    break;
                case FAILED:
                    onNetFailed();
                    break;
                case DONE:
                    onNetDone();
                    break;
                case NO_RESPONSE:
                    onNoResponse();
                    break;
                case NO_NETWORK:
                    onNoNetWork();
                    break;
            }
        });

        mViewModel.getError().observe(this, error -> {
            onNetFailed((NetWorkException)error);
        });
    }


    @Override
    public void onNetLoading() {

    }

    @Override
    public void onNetDone() {

    }

    @Override
    public void onNoNetWork() {

    }

    @Override
    public void onNoResponse() {

    }

    @Override
    public void onNetFailed() {

    }

    @Override
    public void onNetFailed(NetWorkException e) {

    }
}
