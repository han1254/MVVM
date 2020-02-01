package com.example.neuq_mvvm_fragmework.rxjava.observe;

/**
 * Time:2020/1/31 13:55
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface Observer<T> {

    void onSubscribe();
    void onNext(T t);
    void onError();
    void onComplete();

}
