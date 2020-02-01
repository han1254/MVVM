package com.example.neuq_mvvm_fragmework;

import com.example.neuq_mvvm_fragmework.model.Repo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Time:2019/11/9 12:07
 * Author: han1254
 * Email: 1254763408@qq.com
 * RepoSearchResponse: 返回的数据
 */
public class RepoSearchResponse {

    @SerializedName("total_count")
    private int total;

    @SerializedName("items")
    private List<Repo> items = new ArrayList<>();

    private int nextPage;

    public List<Repo> getItems() {
        return items;
    }



}
