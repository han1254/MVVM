package com.example.neuq_mvvm_fragmework.mvvm.viewmodel;


import com.example.lib_neuq_mvvm.base.view.UIEvent;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.neuq_mvvm_fragmework.model.Repo;
import com.example.neuq_mvvm_fragmework.model.Repository;
import com.example.neuq_mvvm_fragmework.model.RequestModel;
import com.example.neuq_mvvm_fragmework.paging.ItemFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Time:2020/1/26 23:08
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TestViewModel extends BaseNetWorkViewModel<Repository> {

    private MutableLiveData<List<Repo>> repos;
    private MutableLiveData<RequestModel> request;
    private LiveData<PagedList<Repo>> pagedList;
    static PagedList.Config config;

    public TestViewModel(LifecycleOwner owner) {
        super(new Repository());
        this.repos = new MutableLiveData<>();
        this.request = new MutableLiveData<>();
        this.pagedList = new MutableLiveData<>();
        pagedList = (new LivePagedListBuilder(new ItemFactory(request, repos, mStatus, mError), config)).build();
    }

    public MutableLiveData<RequestModel> getRequest() {
        return request;
    }

    public LiveData<PagedList<Repo>> getPagedList() {
        return pagedList;
    }



    static {
        config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .build();
    }


    @Override
    public Boolean isDialogAuto() {
        return false;
    }

    @Override
    public Boolean isToastAuto() {
        return false;
    }

    @Override
    public List<UIEvent> getDataBindList() {
        List<UIEvent> events = new ArrayList<>();
        events.add(UIEvent.PROGRESS);
        return null;
    }
}
