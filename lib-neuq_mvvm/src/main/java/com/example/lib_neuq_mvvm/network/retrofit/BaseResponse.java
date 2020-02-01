package com.example.lib_neuq_mvvm.network.retrofit;

import com.google.gson.annotations.SerializedName;

import io.reactivex.Flowable;

/**
 * Time:2020/1/23 9:07
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BaseResponse<T> {

    @SerializedName("code")
    protected int code;

    @SerializedName("message")
    protected String message;

    @SerializedName("result")
    protected T content;

    public BaseResponse(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public T getContent() {
        return content;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
