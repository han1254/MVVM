package com.example.neuq_mvvm_fragmework.rxjava.observable;

import com.example.neuq_mvvm_fragmework.rxjava.observe.Emitter;
import com.example.neuq_mvvm_fragmework.rxjava.observe.ObservableOnSubscribe;
import com.example.neuq_mvvm_fragmework.rxjava.observe.Observer;

/**
 * Time:2020/1/31 14:02
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class ObservableCreate<T> extends Observable<T> {

    private ObservableOnSubscribe source;

    public ObservableCreate(ObservableOnSubscribe source) {
        this.source = source;
    }

    @Override
    public void subscribeActual(Observer<T> observer) {
        EmitterCreate<T> emitterCreate = new EmitterCreate<>(observer);
        observer.onSubscribe();
        source.subscribe(emitterCreate);
    }
}
