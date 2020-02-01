package com.example.neuq_mvvm_fragmework.rxjava.observable;

import com.example.neuq_mvvm_fragmework.rxjava.observe.Emitter;
import com.example.neuq_mvvm_fragmework.rxjava.observe.Observer;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.disposables.Disposable;

/**
 * Time:2020/1/31 14:10
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class EmitterCreate<T> extends AtomicReference<Disposable> implements Disposable, Emitter<T> {

    private Observer<T> observer;

    public EmitterCreate(Observer<T> observer) {
        this.observer = observer;
    }

    @Override
    public void onNext(T t) {
        if (!isDisposed()) {
            observer.onNext(t);
        }
    }

    @Override
    public void onError() {
        if (!isDisposed()) {
            try {
                observer.onError();
            } finally {
                dispose();
            }

        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
