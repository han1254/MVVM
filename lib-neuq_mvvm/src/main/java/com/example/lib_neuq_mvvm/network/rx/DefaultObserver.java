package com.example.lib_neuq_mvvm.network.rx;

import android.net.ParseException;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;
import com.example.lib_neuq_mvvm.utils.ContextUtil;
import com.example.lib_neuq_mvvm.utils.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * Time:2020/1/23 21:51
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class DefaultObserver<T> implements Observer<T> {

    static MutableLiveData<NetWorkException> mNetWorkException = new MutableLiveData<>();

    static {
        mNetWorkException.postValue(NetWorkException.NO_ERROR);
    }

    private NetWorkExceptionController controller;

    public DefaultObserver(NetWorkExceptionController controller) {

        this.controller = controller;
    }

    @Override
    public void onNext(T t) {
        ToastUtil.showLong(ContextUtil.getInstance().build().getContext(), "网络请求成功");
        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {

       if (e instanceof HttpException) {
           onException(NetWorkException.BAD_NETWORK);
           controller.badNetWorkError();
       } else if (e instanceof ConnectException
       || e instanceof UnknownHostException) {
           onException(NetWorkException.CONNECT_ERROR);
           controller.connectError();
       } else if (e instanceof InterruptedIOException) {
           onException(NetWorkException.CONNECT_TIMEOUT);
           controller.timeOutError();
       } else if (e instanceof JsonParseException
       || e instanceof JSONException
       || e instanceof ParseException) {
           onException(NetWorkException.PARSE_ERROR);
           controller.parseError();
       } else {
           onException(NetWorkException.UNKNOWN_ERROR);
           controller.unknownError();
       }
    }

    public void onException(NetWorkException status) {
        switch (status) {
            case CONNECT_ERROR:
                ToastUtil.show(ContextUtil.getInstance().build().getContext(), "连接失败。。。");
                break;
            case CONNECT_TIMEOUT:
                ToastUtil.show(ContextUtil.getInstance().build().getContext(), "连接超时。。。");
                break;
            default:
                ToastUtil.show(ContextUtil.getInstance().build().getContext(), "反正你就是错了。。。");
                break;
        }
    }


}
