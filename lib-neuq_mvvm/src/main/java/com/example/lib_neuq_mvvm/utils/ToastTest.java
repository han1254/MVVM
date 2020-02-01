package com.example.lib_neuq_mvvm.utils;

/**
 * Time:2020/1/23 22:36
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class ToastTest {

    public static void test() {
        ToastUtil.show(ContextUtil.getInstance().build().getContext(), "测试toast");
    }
}
