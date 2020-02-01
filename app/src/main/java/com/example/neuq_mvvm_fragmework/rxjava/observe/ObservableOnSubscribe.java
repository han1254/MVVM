package com.example.neuq_mvvm_fragmework.rxjava.observe;

/**
 * Time:2020/1/31 13:56
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);

}
