package com.example.lib_neuq_mvvm.base.viewmodel;

import android.os.Bundle;

/**
 * Time:2020/1/30 12:06
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface IViewModel {

    void showDialog(String content);

    void dismissDialog();

    void showToast(String content, Boolean isLong);

    void startActivity(Class<?> clz, Bundle bundle);

    void startActivity(Class<?> clz);

    void transToFragment(Bundle bundle);

    void customizeFuc(Object o);
}
