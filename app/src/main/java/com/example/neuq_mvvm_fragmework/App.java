package com.example.neuq_mvvm_fragmework;

import android.app.Application;
import android.content.Context;

import com.example.lib_neuq_mvvm.utils.ContextUtil;

/**
 * Time:2020/1/23 9:36
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class App extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ContextUtil.getInstance().register(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }
}
