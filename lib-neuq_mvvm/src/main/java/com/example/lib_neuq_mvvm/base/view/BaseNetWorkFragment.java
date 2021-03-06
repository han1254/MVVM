package com.example.lib_neuq_mvvm.base.view;

import android.os.Bundle;
import android.view.View;

import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

/**
 * Time:2020/2/1 11:16
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class BaseNetWorkFragment<D extends ViewDataBinding, VM extends BaseNetWorkViewModel> extends BaseFragment<D, VM> implements INetWorkView {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
