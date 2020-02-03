package com.example.neuq_mvvm_fragmework.mvvm.view;

import android.view.View;

import com.example.lib_neuq_mvvm.base.view.BaseActivity;
import com.example.lib_neuq_mvvm.base.view.BaseNetWorkActivity;
import com.example.lib_neuq_mvvm.base.view.MultiStatusActivity;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseViewModel;
import com.example.lib_neuq_mvvm.livedata.LiveDataBus;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.base.Resource;
import com.example.lib_neuq_mvvm.utils.ToastUtil;
import com.example.neuq_mvvm_fragmework.R;
import com.example.neuq_mvvm_fragmework.databinding.ActivityTabTestBinding;
import com.example.neuq_mvvm_fragmework.model.MsgModel;
import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TabTestViewModel;
import com.example.neuq_mvvm_fragmework.mvvm.viewmodel.TabTestViewModelFactory;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class TabTestActivity extends MultiStatusActivity<ActivityTabTestBinding, TabTestViewModel> {

    private TabTestViewModelFactory factory;
    private MutableLiveData<MsgModel> liveData;

    @Override
    protected TabTestViewModel getViewModel() {
        factory = new TabTestViewModelFactory();
        return factory.create(TabTestViewModel.class);
    }

    @Override
    protected void initView() {

        mDataBinding.progressbar2.setVisibility(View.GONE);

        mViewModel.getLiveData().observe(this, n -> {
            mDataBinding.tvContent1.setText(n.getResult());
        });

        mViewModel.getLiveData2().observe(this, n ->  {
            if (n == null) {
                mDataBinding.tvContent2.setText("数据为空");
            } else {
                mDataBinding.tvContent2.setText(n.getResult());

            }
        });

        LiveDataBus.get()
                .with("saveData1", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        ToastUtil.showLong(getApplicationContext(), "数据1存到本地");
                    }
                });

        LiveDataBus.get()
                .with("saveData2", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        mDataBinding.isSave.setText("存储");
                    }
                });

        mDataBinding.btnNet.setOnClickListener(v -> {

            mViewModel.getRemote2(true).observe(this, n -> {
                mDataBinding.tvContent2.setText(n.getResult());
                mDataBinding.tvSource2.setText("网络");

            });
//            mViewModel.getRemote2(true);
//            mDataBinding.tvSource2.setText("网络");


        });
    }

    @Override
    protected void onMultiStatusChange(String key, NetWorkStatus status) {
        if (key.equals("strStatus1")) {
            if (status == NetWorkStatus.LOADING) {
                mDataBinding.progress1.setVisibility(View.VISIBLE);
            } else {
                mDataBinding.progress1.setVisibility(View.GONE);
            }
        } else if (key.equals("strStatus2")) {
            if (status == NetWorkStatus.LOADING) {
                mDataBinding.tvStatus2.setText("加载中。。。");
            } else {
                mDataBinding.tvStatus2.setText("加载完成！( •̀ ω •́ )y");
            }
        }
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
