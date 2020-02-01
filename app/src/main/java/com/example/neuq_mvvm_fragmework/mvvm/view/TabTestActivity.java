package com.example.neuq_mvvm_fragmework.mvvm.view;

import com.example.lib_neuq_mvvm.base.view.BaseActivity;
import com.example.lib_neuq_mvvm.base.view.BaseNetWorkActivity;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseViewModel;
import com.example.neuq_mvvm_fragmework.R;
import com.example.neuq_mvvm_fragmework.databinding.ActivityTabTestBinding;
import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TabTestViewModel;

import androidx.lifecycle.ViewModelProviders;

public class TabTestActivity extends BaseNetWorkActivity<ActivityTabTestBinding, TabTestViewModel> {


    @Override
    protected TabTestViewModel getViewModel() {
        return ViewModelProviders.of(this).get(TabTestViewModel.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_test;
    }

    @Override
    protected void loadData() {

    }

}
