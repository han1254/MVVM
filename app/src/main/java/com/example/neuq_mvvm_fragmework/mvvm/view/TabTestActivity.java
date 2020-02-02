package com.example.neuq_mvvm_fragmework.mvvm.view;

import android.view.View;

import com.example.lib_neuq_mvvm.base.view.BaseActivity;
import com.example.lib_neuq_mvvm.base.view.BaseNetWorkActivity;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseViewModel;
import com.example.lib_neuq_mvvm.network.base.Resource;
import com.example.neuq_mvvm_fragmework.R;
import com.example.neuq_mvvm_fragmework.databinding.ActivityTabTestBinding;
import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TabTestViewModel;
import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TabTestViewModelFactory;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class TabTestActivity extends BaseNetWorkActivity<ActivityTabTestBinding, TabTestViewModel> {

    private TabTestViewModelFactory factory;
    private MutableLiveData<Resource<String>> liveData;

    @Override
    protected TabTestViewModel getViewModel() {
        factory = new TabTestViewModelFactory();
        return factory.create(TabTestViewModel.class);
    }

    @Override
    protected void initView() {
        liveData.observe(this, new Observer<Resource<String>>() {
            @Override
            public void onChanged(Resource<String> stringResource) {
                mViewModel.getUIController().getShowToastEvent().setValue(new BaseViewModel.ToastWrapper(stringResource.getData(), true));
            }
        });
    }

    @Override
    public void onNetLoading() {
       mViewModel.getUIController().getShowProgressBarEvent().setValue(true);
    }

    @Override
    public void onNetDone() {
        mViewModel.getUIController().getShowProgressBarEvent().setValue(false);
    }

    @Override
    protected void showProgressBar(Boolean isShow) {
        mDataBinding.progressbar2.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_test;
    }

    @Override
    protected void loadData() {
        liveData = mViewModel.getLiveData();
        //getViewModel()方法比loadData()更先调用，一定切记不要在getViewModel中调用
        //在loadData()中初始化的数据
    }

}
