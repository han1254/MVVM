package com.example.neuq_mvvm_fragmework.rxjava.observe;

/**
 * Time:2020/1/31 13:58
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface Emitter<T> {
    void onNext(T t);

    void onError();
}
