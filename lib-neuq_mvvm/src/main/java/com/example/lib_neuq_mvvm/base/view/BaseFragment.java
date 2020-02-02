package com.example.lib_neuq_mvvm.base.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lib_neuq_mvvm.base.viewmodel.BaseViewModel;
import com.example.lib_neuq_mvvm.utils.ToastUtil;

import java.util.Objects;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * Time:2020/1/23 8:13
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public abstract class BaseFragment<D extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    protected D mDataBinding;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);
        mDataBinding = DataBindingUtil.bind(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getViewModel();
        loadData();
        initView();
        setUIReaction();
    }

    protected void setUIReaction() {
        //展示Toast
        if (!mViewModel.getUIController().getShowToastEvent().getDataBind()) {
            mViewModel.getUIController().getShowToastEvent().observe(this, toastWrapper -> {
                //判断isAuto，是否需要自定义Toast。
                if (mViewModel.getUIController().getShowToastEvent().getIsAuto()) {
                    if (toastWrapper.isLong()) {
                        ToastUtil.showLong(getContext(), toastWrapper.getContent());
                    } else {
                        ToastUtil.show(getContext(), toastWrapper.getContent());
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
                Intent intent = new Intent(getActivity(), clz);
                if (bundle == null) {
                    startActivity(intent);
                } else {
                    startActivity(intent, bundle);
                }
            });
        }

        //结束Activity
        if (!mViewModel.getUIController().getFinishEvent().getDataBind()) {
            mViewModel.getUIController().getFinishEvent().observe(this, aVoid -> {
                Objects.requireNonNull(getActivity()).finish();
            });
        }


        //关闭Dialog
        if (!mViewModel.getUIController().getDismissDialogEvent().getDataBind()) {
            mViewModel.getUIController().getDismissDialogEvent().observe(this, aVoid -> {
                disMissDialog();
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

    /**
     * 传统方法跳转Fragment（栈式）
     * @param layoutId
     * @param fragment
     */
    protected void transtToFragmentByStack(int layoutId, Fragment fragment) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(layoutId, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    /**
     * 传统方法跳转Fragment（非栈式）
     * @param layoutId
     * @param fragment
     */
    protected void transToFragment(int layoutId, Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(layoutId, fragment, null)
                .commit();
    }

    /**
     * 返回
     */
    protected void back() {

        getActivity().getSupportFragmentManager().popBackStack();

    }

    /**
     * 利用Navigation进行跳转Fragment
     * @param action
     * @param bundle
     */
    protected void navigateToFragment(int action, Bundle bundle) {

        if (bundle ==  null) {
            Navigation.findNavController(getView()).navigate(action);
        } else {
            Navigation.findNavController(getView()).navigate(action, bundle);
        }
    }

    /**
     * 利用Navigation进行跳转Fragment
     * @param action
     */
    protected void navigateToFragment(int action) {
        navigateToFragment(action, null);
    }

    /**
     * 展示progressBar
     * @param isShow
     */
    protected void showProgressBar(Boolean isShow) {

    }

    /**
     * 自定义的一些方法，如动画等
     * @param object
     */
    protected void customizeFunc(Object object) {

    }

    /**
     * 停止显示progressBar
     */
    protected void disMissDialog() {

    }

    /**
     * 显示Dialog
     * @param str
     */
    protected void showDialog(String str) {

    }

    /**
     * 显示Toast
     * @param toastWrapper
     */
    protected void showToast(BaseViewModel.ToastWrapper toastWrapper) {

    }

    protected abstract void initView();

    protected abstract void loadData();

    public abstract int getLayoutId();

    protected abstract VM getViewModel();


}
