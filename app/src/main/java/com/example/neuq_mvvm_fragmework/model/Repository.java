package com.example.neuq_mvvm_fragmework.model;

import com.example.lib_neuq_mvvm.base.model.BaseRepository;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.base.Resource;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;
import com.example.lib_neuq_mvvm.network.retrofit.BaseResponse;
import com.example.lib_neuq_mvvm.network.retrofit.GetApiService;
import com.example.lib_neuq_mvvm.network.rx.DefaultObserver;
import com.example.lib_neuq_mvvm.network.rx.NetWorkExceptionController;
import com.example.neuq_mvvm_fragmework.Api;
import com.example.neuq_mvvm_fragmework.RepoSearchResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time:2020/1/23 22:54
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class Repository extends BaseRepository {

    private MutableLiveData<Resource<String>> text = new MutableLiveData<>();

    public static Observable<RepoSearchResponse> getRepos(String name, int page, int count) {
        return GetApiService.getApiService(Api.class, "https://api.github.com/")
                .getRepos(name, page, count);
    }

}
