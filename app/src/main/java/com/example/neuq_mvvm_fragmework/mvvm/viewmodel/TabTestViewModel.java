package com.example.neuq_mvvm_fragmework.mvvm.viewmodel;

import com.example.lib_neuq_mvvm.base.view.UIEvent;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.base.viewmodel.MultiStatusViewModel;
import com.example.lib_neuq_mvvm.databinding.NetworkStatusLayoutBindingImpl;
import com.example.lib_neuq_mvvm.livedata.LiveDataBus;
import com.example.lib_neuq_mvvm.livedata.UnPeekLiveData;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.base.Resource;
import com.example.lib_neuq_mvvm.network.exception.NetWorkException;
import com.example.neuq_mvvm_fragmework.model.MsgModel;
import com.example.neuq_mvvm_fragmework.model.TabTestRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/1/31 16:43
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TabTestViewModel extends MultiStatusViewModel<TabTestRepository> {

    private MutableLiveData<MsgModel> liveData1;
    private UnPeekLiveData<NetWorkStatus> strStatus1;
    private UnPeekLiveData<NetWorkException> strException1;
    private MutableLiveData<MsgModel> liveData2;
    private UnPeekLiveData<NetWorkStatus> strStatus2;
    private UnPeekLiveData<NetWorkException> strException2;

    public TabTestViewModel(TabTestRepository repository) {
        super(repository);
        liveData1 = repository.getRemote();
        //为了更加方便地监测数据变动，（其实是我太菜了，如果采用
        //liveData2 = repository.getRemote2()这种形式，会出现很多
        //出乎意料的错误，建议直接将liveData传入相关的方法，在请求数据时候
        //直接改变这个数据的引用
        repository.getRemote2(false, liveData2);
    }

    public LiveData<MsgModel> getRemote2(boolean isFetch) {
        return repository.getRemote2(isFetch, liveData2);
    }

    @Override
    protected void loadData() {
        liveData1 = new MutableLiveData<>();
        liveData2 = new MutableLiveData<>();
        strStatus1 = new UnPeekLiveData<>();
        strException1 = new UnPeekLiveData<>();
        strException2 = new UnPeekLiveData<>();
        strStatus2 = new UnPeekLiveData<>();
    }

    @Override
    protected void registerStateMap() {
        mStatusMap.put("strStatus1", strStatus1);
        mErrorMap.put("strException1", strException1);
        mStatusMap.put("strStatus2", strStatus2);
        mErrorMap.put("strException2", strException2);
    }

    public MutableLiveData<MsgModel> getLiveData() {
        return liveData1;
    }

    public MutableLiveData<MsgModel> getLiveData2() {
        return liveData2;
    }

    @Override
    public Boolean isDialogAuto() {
        return true;
    }

    @Override
    public Boolean isToastAuto() {
        return true;
    }

    @Override
    public List<UIEvent> getDataBindList() {
        return null;
    }
}
