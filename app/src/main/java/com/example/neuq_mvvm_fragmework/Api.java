package com.example.neuq_mvvm_fragmework;

import com.example.lib_neuq_mvvm.network.retrofit.BaseResponse;
import com.example.neuq_mvvm_fragmework.model.MsgModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Time:2020/1/23 22:56
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public interface Api {
    @GET("getSongPoetry")
    Observable<MsgModel> getString(@Query("page") int page, @Query("count") int count);


    @GET("search/repositories?sort=stars")
    Observable<RepoSearchResponse> getRepos(
            @Query("q") String query,
            @Query("page") int pge,
            @Query("per_page") int itemsPerPage);

}
