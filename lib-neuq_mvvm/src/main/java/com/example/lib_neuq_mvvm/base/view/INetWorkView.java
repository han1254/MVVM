package com.example.lib_neuq_mvvm.base.view;

import com.example.lib_neuq_mvvm.network.exception.NetWorkException;

/**
 * Time:2020/1/29 20:57
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface INetWorkView {

    void onNetLoading();

    void onNetDone();

    void onNoNetWork();

    void onNoResponse();

    void onNetFailed();

    void onNetFailed(NetWorkException e);
}
