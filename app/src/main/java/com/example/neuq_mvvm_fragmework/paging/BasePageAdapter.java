package com.example.neuq_mvvm_fragmework.paging;

import com.example.lib_neuq_mvvm.base.recyclerview.adapter.listadapter.BaseDataBindingViewHolder;
import com.example.lib_neuq_mvvm.base.recyclerview.adapter.listadapter.BaseDiffUtil;
import com.example.neuq_mvvm_fragmework.R;
import com.example.neuq_mvvm_fragmework.model.Repo;
import com.example.neuq_mvvm_fragmework.databinding.ListItemPlantBinding;

/**
 * Time:2020/1/28 12:57
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BasePageAdapter extends com.example.lib_neuq_mvvm.base.recyclerview.adapter.pagedadapter.BasePagingListAdapter<Repo, ListItemPlantBinding> {


    public BasePageAdapter(BaseDiffUtil<Repo> baseDiffUtil) {
        super(baseDiffUtil);
    }

    @Override
    protected void setClickCallBack(BaseDataBindingViewHolder<ListItemPlantBinding> holder, int position) {

    }

    @Override
    public void bindView(BaseDataBindingViewHolder<ListItemPlantBinding> holder, Repo data) {
        holder.getDataBinding().setRepo(data);
        holder.getDataBinding().setOwner(data.getOwner());
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_item_plant;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
