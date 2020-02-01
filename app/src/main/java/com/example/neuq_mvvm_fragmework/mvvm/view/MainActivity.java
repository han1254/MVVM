package com.example.neuq_mvvm_fragmework.mvvm.view;

import android.view.View;

import com.example.lib_neuq_mvvm.base.view.BaseActivity;
import com.example.lib_neuq_mvvm.base.recyclerview.adapter.listadapter.BaseDiffUtil;
import com.example.lib_neuq_mvvm.base.view.BaseNetWorkActivity;
import com.example.neuq_mvvm_fragmework.R;
import com.example.neuq_mvvm_fragmework.databinding.ActivityMainBinding;
import com.example.neuq_mvvm_fragmework.databinding.ListItemPlantBinding;
import com.example.neuq_mvvm_fragmework.model.Repo;
import com.example.neuq_mvvm_fragmework.model.RequestModel;
import com.example.neuq_mvvm_fragmework.paging.BasePageAdapter;
import com.example.neuq_mvvm_fragmework.util.InjectorUtil;
import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TestViewModel;


public class MainActivity extends BaseNetWorkActivity<ActivityMainBinding, TestViewModel> {

    private com.example.lib_neuq_mvvm.base.recyclerview.adapter.pagedadapter.BasePagingListAdapter<Repo, ListItemPlantBinding> adapter;

    @Override
    protected TestViewModel getViewModel() {
        return  InjectorUtil.getTestViewModelFactory(getApplication(), this).create(TestViewModel.class);
    }

    @Override
    protected void initView() {
        mDataBinding.plantList.setAdapter(adapter);
        mDataBinding.setViewmodel(mViewModel);
        mViewModel.getPagedList().observe(this, data -> {
            adapter.submitList(data);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {
        adapter = new BasePageAdapter(new BaseDiffUtil<>());
        mViewModel.getRequest().setValue(new RequestModel("android", 1, 8));
    }

    @Override
    public void onNetDone() {
        mViewModel.getUIController().getShowProgressBarEvent().setValue(false);
    }

    @Override
    public void onNetFailed() {
        mViewModel.getUIController().getShowProgressBarEvent().setValue(false);
    }

    @Override
    public void onNetLoading() {

        mViewModel.getUIController().getShowProgressBarEvent().setValue(true);

    }


}
