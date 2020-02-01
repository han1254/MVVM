package com.example.neuq_mvvm_fragmework.rxjava.observable;

import com.example.neuq_mvvm_fragmework.rxjava.observe.ObservableOnSubscribe;
import com.example.neuq_mvvm_fragmework.rxjava.observe.Observer;


/**
 * Time:2020/1/31 14:00
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class Observable<T> {

    public static <T> ObservableCreate create(ObservableOnSubscribe<T> observableOnSubscribe) {
        return new ObservableCreate<T>(observableOnSubscribe);
    }

    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    public abstract void subscribeActual(Observer<T> observer);

}
