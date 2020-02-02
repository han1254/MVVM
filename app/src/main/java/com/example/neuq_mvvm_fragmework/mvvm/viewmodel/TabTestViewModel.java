package com.example.neuq_mvvm_fragmework.mvvm.viewmodel;

import com.example.lib_neuq_mvvm.base.view.UIEvent;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.lib_neuq_mvvm.databinding.NetworkStatusLayoutBindingImpl;
import com.example.lib_neuq_mvvm.network.base.NetWorkStatus;
import com.example.lib_neuq_mvvm.network.base.Resource;
import com.example.neuq_mvvm_fragmework.model.TabTestRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Time:2020/1/31 16:43
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TabTestViewModel extends BaseNetWorkViewModel<TabTestRepository> {

    private MutableLiveData<Resource<String>> liveData;
    private MutableLiveData<NetWorkStatus> mBannerStatus;
    private MutableLiveData<NetWorkStatus> mGoodsStatus;

    public TabTestViewModel(TabTestRepository repository) {
        super(repository);
        liveData = repository.getRemote();
    }

    public MutableLiveData<Resource<String>> getLiveData() {
        return liveData;
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
