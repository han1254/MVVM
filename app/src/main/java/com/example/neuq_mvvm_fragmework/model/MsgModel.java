package com.example.neuq_mvvm_fragmework.model;

import com.example.lib_neuq_mvvm.network.base.INetWorkResponseModel;

/**
 * Time:2020/2/3 9:14
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class MsgModel implements INetWorkResponseModel {

    /**
     * code : 200
     * message : 成功!
     * result : 因有人恶意刷接口，导致接口调用频繁，接口已经不能稳定运行，所以计划近期下线，积德吧朋友，如果长期如此，所有接口将面临关闭。
     */

    private int code;
    private String message;
    private String result;


    public MsgModel(int code, String message, String result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
