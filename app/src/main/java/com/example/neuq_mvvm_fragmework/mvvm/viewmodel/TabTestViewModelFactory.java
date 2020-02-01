package com.example.neuq_mvvm_fragmework.mvvm.viewmodel;

import com.example.neuq_mvvm_fragmework.model.TabTestRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Time:2020/2/1 15:37
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TabTestViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TabTestViewModel(new TabTestRepository());
    }
}
