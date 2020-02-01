package com.example.neuq_mvvm_fragmework;

import com.example.lib_neuq_mvvm.livedata.SingleObserverLiveData;

import org.junit.Test;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest implements LifecycleOwner {

    private SingleObserverLiveData<Boolean> singleObserverLiveData = new SingleObserverLiveData<>();

  @Test
    public void changData() {

      singleObserverLiveData.observe(this, new Observer<Boolean>() {
          @Override
          public void onChanged(Boolean aBoolean) {

          }
      });
      singleObserverLiveData.postValue(true);

  }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}