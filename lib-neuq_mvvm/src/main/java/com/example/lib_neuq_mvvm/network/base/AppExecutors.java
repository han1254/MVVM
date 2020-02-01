package com.example.lib_neuq_mvvm.network.base;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Time:2020/1/25 17:08
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class AppExecutors {

    private Executor disIO;
    private Executor networkIO;
    private Executor mainThread;

    public AppExecutors() {
        this.disIO = Executors.newSingleThreadExecutor();
        this.networkIO = Executors.newFixedThreadPool(3);
        this.mainThread = new MainThreadExecutor();
    }

    public Executor getDisIO() {
        return disIO;
    }

    public Executor getNetworkIO() {
        return networkIO;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }

}
