package com.example.neuq_mvvm_fragmework.model;

/**
 * Time:2020/1/28 11:11
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class RequestModel {
    private String query;
    private int page;
    private int count;

    public RequestModel(String query, int page, int count) {
        this.query = query;
        this.page = page;
        this.count = count;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getQuery() {
        return query;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }
}
