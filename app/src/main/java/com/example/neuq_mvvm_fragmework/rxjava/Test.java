package com.example.neuq_mvvm_fragmework.rxjava;

import com.example.neuq_mvvm_fragmework.rxjava.observable.Observable;
import com.example.neuq_mvvm_fragmework.rxjava.observable.ObservableCreate;
import com.example.neuq_mvvm_fragmework.rxjava.observe.Emitter;
import com.example.neuq_mvvm_fragmework.rxjava.observe.ObservableOnSubscribe;
import com.example.neuq_mvvm_fragmework.rxjava.observe.Observer;

/**
 * Time:2020/1/31 14:21
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class Test {

    public void test() {
        ObservableCreate.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("sb");
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
