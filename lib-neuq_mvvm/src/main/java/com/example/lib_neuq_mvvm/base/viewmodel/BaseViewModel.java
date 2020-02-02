package com.example.lib_neuq_mvvm.base.viewmodel;

import android.os.Bundle;
import android.util.Log;

import com.example.lib_neuq_mvvm.base.view.UIEvent;
import com.example.lib_neuq_mvvm.livedata.UIEventSingleLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.ViewModel;

/**
 * Time:2020/1/22 20:06
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 *     本来想改成继承AndroidViewModel，从stackoverflow中看到一个回答
 * https://stackoverflow.com/questions/44148966/androidviewmodel-vs-viewmodel
 * 发现AndroidViewModel只是多了一个Application的引用，但是转念一想，全局获得Application
 * 的方法很多，而且由于Application基本不会引起内存泄漏，所以就不改了。
 *
 *     根据这个谷歌公司的小姐姐的文章（https://medium.com/androiddevelopers/viewmodels-a-simple-example-ed5ac416317e）
 * 来看的话，ViewModel应该与View分离，本ViewModel采取了谷歌推崇的数据驱动视图的思想。
 * “In general, you’ll make a ViewModel class for each screen in your app. This ViewModel class will hold all of the data
 * associated with the screen and have getters and setters for the stored data. This separates the code to display the UI,
 * which is implemented in your Activities and Fragments, from your data, which now lives in the ViewModel.”
 *
 * “在常规的开发模式中，数据变化需要更新UI的时候，需要先获取UI控件的引用，然后再更新UI。获取用户的输入和操作也需要通过UI控件的引用。
 * 在MVVM中，这些都是通过数据驱动来自动完成的，数据变化后会自动更新UI，UI的改变也能自动反馈到数据层，数据成为主导因素。这样MVVM层在
 * 业务逻辑处理中只要关心数据，不需要直接和UI打交道，在业务处理过程中简单方便很多。” —— 美团技术团队
 *
 * 故对于基础的视图事件，BaseViewModel采取了利用数据控制的方法，在View中根据逻辑改动UILiveData的数据，设计好UI事件的对应行为，每个UILiveData
 * 变动，都会调用相应的事件（着并不是说你只能使用这几种事件，你完全可以设计其他展示事件，这个只是为你提供了一个统一的事件展示）
 *
 * ----->>> 需要注意的是，由于UI事件是单观察者LiveData，你如果想使用DataBinding去观察UIEventLiveData的话，必须通过getDataBindList()，返回
 * 使用DataBinding的事件，这样代码就会自动在视图中停止对该UIEventLiveData的观察
 */
public abstract class BaseViewModel extends ViewModel implements IViewModel {

    public static final String CLASS_NAME = "class";
    public static final String BUNDLE_NAME = "bundle";

    protected UIController uiController;

    public BaseViewModel() {
        this.uiController = new UIController();
        uiController.getShowToastEvent().setAuto(isToastAuto());
        uiController.getShowDialogEvent().setAuto(isDialogAuto());
        if (getDataBindList() != null) {
            for (UIEvent event :
                    getDataBindList()) {
                switch (event) {
                    case TOAST:
                        uiController.getShowToastEvent().setDataBind(true);
                        break;
                    case DIALOG:
                        uiController.getShowDialogEvent().setDataBind(true);
                        break;
                    case PROGRESS:
                        uiController.getShowProgressBarEvent().setDataBind(true);
                        break;
                    case START_ACTIVITY:
                        uiController.getStartActivityEvent().setDataBind(true);
                        break;
                    case FINISH:
                        uiController.getFinishEvent().setDataBind(true);
                        break;
                    case CUSTOMISE:
                        uiController.getCustomizeEvent().setDataBind(true);
                        break;
                    default:
                        Log.e("Unknown Type","不是UIEvent");
                        break;
                }
            }
        }
    }

    public UIController getUIController() {

        if (uiController == null) {
            return new UIController();
        }

        return uiController;
    }

    @Override
    public void showDialog(String content) {
        uiController.getShowDialogEvent().postValue(content);
    }

    @Override
    public void dismissDialog() {

        uiController.getDismissDialogEvent().call();
    }


    @Override
    public void showToast(String content, Boolean isLong) {

        uiController.getShowToastEvent().postValue(new ToastWrapper(content, isLong));
    }

    @Override
    public void startActivity(Class<?> clz, Bundle bundle) {
        Map<String, Object> prams = new HashMap<>();
        prams.put(CLASS_NAME, clz);
        if (bundle != null) {
            prams.put(BUNDLE_NAME, bundle);
        }

        uiController.startActivityEvent.postValue(prams);
    }

    @Override
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    @Override
    public void transToFragment(Bundle bundle) {

    }

    @Override
    public void customizeFuc(Object o) {
        getUIController().customizeEvent.postValue(o);
    }

    /**
     * 是否自动显示Dialog
     * @return
     */
    public  Boolean isDialogAuto() {
        return false;
    }

    /**
     * 是否自动显示Toast
     * @return
     */
    public Boolean isToastAuto() {
        return false;
    }

    /**
     * 由于单一观察者模式的局限，如果一个UIEventLiveData
     * @return
     */
    public abstract List<UIEvent> getDataBindList();

    public final class UIController {

        private UIEventSingleLiveData<ToastWrapper> showToastEvent;
        private UIEventSingleLiveData<String> showDialogEvent;
        private UIEventSingleLiveData<Void> dismissDialogEvent;
        private UIEventSingleLiveData<Boolean> showProgressBarEvent;
        private UIEventSingleLiveData<Map<String, Object>> startActivityEvent;
        private UIEventSingleLiveData<Bundle> transToFragmentEvent;
        private UIEventSingleLiveData<Void> finishEvent;
        private UIEventSingleLiveData<Object> customizeEvent;

        public UIEventSingleLiveData<ToastWrapper> getShowToastEvent() {

           showToastEvent = getUIEventLiveData(UIEvent.TOAST, showToastEvent);
           return showToastEvent;

        }

        public UIEventSingleLiveData<String> getShowDialogEvent() {

           showDialogEvent = getUIEventLiveData(UIEvent.DIALOG, showDialogEvent);
           return showDialogEvent;

        }

        public UIEventSingleLiveData<Void> getDismissDialogEvent() {

           dismissDialogEvent = getUIEventLiveData(UIEvent.DIALOG, dismissDialogEvent);
            return dismissDialogEvent;

        }

        public UIEventSingleLiveData<Boolean> getShowProgressBarEvent() {

           showProgressBarEvent = getUIEventLiveData(UIEvent.PROGRESS, showProgressBarEvent);
            return showProgressBarEvent;
        }

        public UIEventSingleLiveData<Map<String, Object>> getStartActivityEvent() {

           startActivityEvent = getUIEventLiveData(UIEvent.START_ACTIVITY, startActivityEvent);
           return startActivityEvent;

        }

        public UIEventSingleLiveData<Bundle> getTransToFragmentEvent() {

           transToFragmentEvent = getUIEventLiveData(UIEvent.TRANS_TO_FRAGMENT, transToFragmentEvent);
           return transToFragmentEvent;

        }

        public UIEventSingleLiveData<Void> getFinishEvent() {

           finishEvent = getUIEventLiveData(UIEvent.FINISH, finishEvent);
           return finishEvent;

        }

        public UIEventSingleLiveData<Object> getCustomizeEvent() {

           customizeEvent = getUIEventLiveData(UIEvent.CUSTOMISE, customizeEvent);
           return customizeEvent;

        }

        private <T> UIEventSingleLiveData <T> getUIEventLiveData(UIEvent event, UIEventSingleLiveData<T> liveData) {

           if (liveData == null) {
               liveData = new UIEventSingleLiveData<>(event);
           }

           return liveData;

        }
    }

    public static class ToastWrapper {
        private String content;
        private Boolean isLong;

        public ToastWrapper(String content, Boolean isLong) {
            this.content = content;
            this.isLong = isLong;
        }

        public String getContent() {
            return content;
        }

        public Boolean isLong() {
            return isLong;
        }
    }

}
