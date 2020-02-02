package com.example.neuq_mvvm_fragmework.mvvm.view;

import android.view.View;
import android.widget.Toast;

import com.example.lib_neuq_mvvm.base.recyclerview.adapter.listadapter.BaseDiffUtil;
import com.example.lib_neuq_mvvm.base.view.BaseNetWorkActivity;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseViewModel;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;
import com.example.lib_neuq_mvvm.utils.ToastUtil;
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
        Toast.makeText(this, "完成了完成了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetFailed() {

        mViewModel.getUIController().getShowProgressBarEvent().setValue(false);

    }

    @Override
    public void onNetLoading() {

        mViewModel.getUIController().getShowProgressBarEvent().setValue(true);
//        BaseViewModel.ToastWrapper wrapper = new BaseViewModel.ToastWrapper("正在加载", true);
//        mViewModel.getUIController().getShowToastEvent().setValue(wrapper);

    }

    @Override
    public void onNetFailed(NetWorkException e) {
        if (e == NetWorkException.BAD_NETWORK) {
            BaseViewModel.ToastWrapper wrapper = new BaseViewModel.ToastWrapper("HTTP错误", true);
            mViewModel.getUIController().getShowToastEvent().setValue(wrapper);
        }
    }

    /**
     * 如果我在TestViewModel中getDataBindList()中返回的List中包含PROGRESS，
     * 则说明我想利用DataBinding来展示，则数据变动不会调用showProgressBar()方法，
     * 而是调用ListBindingAdapter中利用@BindingAdapter注解的方法
     * @param isShow
     */
    @Override
    protected void showProgressBar(Boolean isShow) {
        mDataBinding.progressbar.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 如果我在TestViewModel中将isToastAuto()返回为false，说明是我想要自定义显示Toast
     * 则数据变动时会调用showToast方法，如果返回为true，那我就使用框架提供的Toast
     *
     * Dialog也是同理，但是Dialog我还没写，就是个空方法，以后再补吧
     * @param toastWrapper
     */
    @Override
    protected void showToast(BaseViewModel.ToastWrapper toastWrapper) {
        ToastUtil.showLong(this, "这是自定义的很酷很酷的Toast");
    }
}
