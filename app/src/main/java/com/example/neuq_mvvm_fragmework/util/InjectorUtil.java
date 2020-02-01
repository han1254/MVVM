package com.example.neuq_mvvm_fragmework.util;

import android.app.Application;

import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TestViewModelFactory;

import androidx.lifecycle.LifecycleOwner;

/**
 * Time:2020/1/28 13:55
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class InjectorUtil {

    public static TestViewModelFactory getTestViewModelFactory(Application application, LifecycleOwner owner) {
        return new TestViewModelFactory(application, owner);
    }

}
