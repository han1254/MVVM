package com.example.lib_neuq_mvvm.base.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lib_neuq_mvvm.base.viewmodel.BaseViewModel;
import com.example.lib_neuq_mvvm.utils.ToastUtil;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Time:2020/1/23 22:59
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class BaseActivity<D extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    protected D mDataBinding;
    protected V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        if (mDataBinding == null) {
            Log.d("LOST", "onCreate: DataBinding couldn't be found");
        } else {
            mDataBinding.setLifecycleOwner(this);
        }
        mViewModel = getViewModel();
        loadData();
        initView();
        setUIReaction();
    }

    @Override
    protected void onDestroy() {
        mDataBinding.unbind();
        mViewModel = null;
        super.onDestroy();
    }

    /**
     * 将基础的UI展示事件与ViewModel中的LiveData构成观察，
     * 一旦ViewModel中的相关LiveData变动，则会触发相应方
     * 法。
     */
    private void setUIReaction() {

        //展示Toast
       if (!mViewModel.getUIController().getShowToastEvent().getDataBind()) {
           mViewModel.getUIController().getShowToastEvent().observe(this, toastWrapper -> {
               //判断isAuto，是否需要自定义Toast。
               if (mViewModel.getUIController().getShowToastEvent().getIsAuto()) {
                   if (toastWrapper.isLong()) {
                       ToastUtil.showLong(getApplicationContext(), toastWrapper.getContent());
                   } else {
                       ToastUtil.show(getApplicationContext(), toastWrapper.getContent());
                   }
               } else {
                   showToast(toastWrapper);
               }
           });

       }
        //展示Dialog
       if (!mViewModel.getUIController().getShowDialogEvent().getDataBind()) {
           mViewModel.getUIController().getShowDialogEvent().observe(this, str -> {
               if (mViewModel.getUIController().getShowDialogEvent().getIsAuto()) {
                   //TODO:
               } else {
                   //自定义dialog
                   showDialog(str);
               }

           });
       }

        //开启Activity
       if (!mViewModel.getUIController().getStartActivityEvent().getDataBind()) {
           mViewModel.getUIController().getStartActivityEvent().observe(this, stringObjectMap -> {
               Class<?> clz = (Class<?>) stringObjectMap.get(BaseViewModel.CLASS_NAME);
               Bundle bundle = (Bundle) stringObjectMap.get(BaseViewModel.BUNDLE_NAME);
               Intent intent = new Intent(this, clz);
               if (bundle == null) {
                   startActivity(intent);
               } else {
                   startActivity(intent, bundle);
               }
           });

       }
        //关闭Dialog
        if (!mViewModel.getUIController().getDismissDialogEvent().getDataBind()) {
            mViewModel.getUIController().getDismissDialogEvent().observe(this, aVoid -> {
                disMissDialog();
            });
        }

        //结束Activity
        if (!mViewModel.getUIController().getFinishEvent().getDataBind()) {
            mViewModel.getUIController().getFinishEvent().observe(this, aVoid -> {
                finish();
            });
        }

        //展示进度条
        if (!mViewModel.getUIController().getShowProgressBarEvent().getDataBind()) {
            mViewModel.getUIController().getShowProgressBarEvent().observe(this, this::showProgressBar);
        }

        //自定义的UI展示，如动画等，可利用此方法去判断object的类型并且调用其他的展示方法
        if (!mViewModel.getUIController().getCustomizeEvent().getDataBind()) {
            mViewModel.getUIController().getCustomizeEvent().observe(this, this::customizeFunc);
        }

    }

    protected void showProgressBar(Boolean isShow) {

    }

    protected void customizeFunc(Object object) {

    }

    protected void disMissDialog() {

    }

    protected void showDialog(String str) {

    }

    protected void showToast(BaseViewModel.ToastWrapper toastWrapper) {

    }

    protected abstract V getViewModel();


    protected abstract void initView();

    protected abstract int getLayoutId();


    protected abstract void loadData();

    /**
     * 在Activity中处理逻辑时候方便快速开启Activity
     * @param clazz
     * @param bundle
     * @param name
     */
    public void startActivity(Class<?> clazz, Bundle bundle, String name) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null && name != null && !"".equals(name)) {
            intent.putExtra(name, bundle);
        } else {
            Log.d("NULL_PARAMS", "startActivity: 开启activity数据不足");
        }
        startActivity(intent);
    }

    public void startActivity(Class<?> clazz) {
        startActivity(clazz, null, null);
    }
}
