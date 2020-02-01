package com.example.lib_neuq_mvvm.livedata;

import com.example.lib_neuq_mvvm.base.view.UIEvent;


/**
 * Time:2020/1/30 17:02
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:主要用于网络状态的展示，对于网络状态，全局最好只有一个展示者
 * 故设置单一观察者LiveData（SingleObserverLiveData)，从而控制网络状态的UI显示
 *
 *
 * 例如：
 * 1.isDataBind = false
 * 你利用Java代码，观察了一个网络状态，当网络状态变动时(会有相应的方法），在相关方法中（如onLoading)
 * 改变UIEventLiveData，如BaseViewModel中的showProgressBarEvent的数据，则会自动调用你设置好的显示
 * Loading的方法。
 *
 * 2.isDataBind = true
 * 如果isDataBind为true，说明使用者对该UIEventLiveData使用了在xml中的dataBinding，由于单一观察者
 * 模式，此时则不应该在Activity中再对showProgressBarEvent进行观察，如果还进行观察的话，dataBinding
 * 则会失效。
 */
public class UIEventSingleLiveData<T> extends SingleObserverLiveData<T> {

    private UIEvent event;
    private Boolean isAuto;
    private Boolean isDataBind;

    public UIEventSingleLiveData(UIEvent event) {
        this(event, false);
    }


    public UIEventSingleLiveData(UIEvent event, Boolean isAuto) {
        this(event, isAuto, false);
    }

    public UIEventSingleLiveData(UIEvent event, Boolean isAuto, Boolean isDataBind) {
        this.event = event;
        this.isAuto = isAuto;
        this.isDataBind = isDataBind;
    }

    public boolean getIsAuto() {
        return this.isAuto;
    }

    public UIEvent getEvent() {
        return event;
    }

    public void setEvent(UIEvent event) {
        this.event = event;
    }

    public void setAuto(Boolean auto) {
        isAuto = auto;
    }

    public void setDataBind(Boolean dataBind) {
        isDataBind = dataBind;
    }

    public Boolean getAuto() {
        return isAuto;
    }

    public Boolean getDataBind() {
        return isDataBind;
    }
}
