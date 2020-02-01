package com.example.lib_neuq_mvvm.utils;

import android.content.Context;

/**
 * Time:2020/1/23 9:27
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class ContextUtil {

    private static final Object mObj = new Object();
    private Builder builder;
    private static ContextUtil INSTANCE = null;
    private ContextUtil() {

    }

    public static ContextUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (mObj) {
                if (INSTANCE == null) {
                    INSTANCE = new ContextUtil();
                }
            }
        }

        return INSTANCE;
    }

    public void register(Context context) {
        builder = new Builder(context);
    }

    public Builder build() {
        return builder;
    }

    public static final class Builder {

        private Context mContext;
        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Context getContext() {
            return mContext;
        }
    }

}
