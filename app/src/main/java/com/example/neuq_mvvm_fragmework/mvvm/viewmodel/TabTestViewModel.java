package com.example.neuq_mvvm_fragmework.mvvm.viewmodel;

import com.example.lib_neuq_mvvm.base.view.UIEvent;
import com.example.lib_neuq_mvvm.base.viewmodel.BaseNetWorkViewModel;
import com.example.neuq_mvvm_fragmework.mvvm.TabTestRepository;

import java.util.List;

/**
 * Time:2020/1/31 16:43
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class TabTestViewModel extends BaseNetWorkViewModel<TabTestRepository> {
    public TabTestViewModel(TabTestRepository repository) {
        super(repository);
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
