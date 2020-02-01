package com.example.lib_neuq_mvvm.network.retrofit;

/**
 * Time:2020/1/23 22:46
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class GetApiService {

    public static <T> T getApiService(Class<T> tClass, String baseUrl) {
        return RetrofitUtils.getRetrofitBuilder(baseUrl).build().create(tClass);
    }

}
