package com.example.neuq_mvvm_fragmework.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Time:2020/1/28 13:50
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TestViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private LifecycleOwner owner;
    public TestViewModelFactory(@NonNull Application application, LifecycleOwner owner) {
        super(application);
        this.owner = owner;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TestViewModel(owner);
    }
}
