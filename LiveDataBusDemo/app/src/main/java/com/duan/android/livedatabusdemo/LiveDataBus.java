package com.duan.android.livedatabusdemo;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * author : Duan
 * time : 2019/07/01
 * desc :
 * version: 1.0
 * </pre>
 */
public class LiveDataBus {

    private final Map<String, MutableLiveData<Object>> bus;

    public LiveDataBus() {
        this.bus = new HashMap<>();
    }

    private static class SingleHolder{
        private static final LiveDataBusBeta DATA_BUS = new LiveDataBusBeta();
    }

    public static LiveDataBusBeta get(){
        return SingleHolder.DATA_BUS;
    }

    public <T> MyMutableLiveData<T> with(String target,Class<T> type){
        if (!bus.containsKey(target)){
            bus.put(target,new MyMutableLiveData<Object>());
        }
        return (MyMutableLiveData<T>) bus.get(target);
    }

    public MyMutableLiveData<Object> with(String target){
        return with(target, Object.class);
    }

    public static class MyMutableLiveData<T> extends MutableLiveData<T>{

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
            super.observe(owner, observer);
            try {
                hook(observer);
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void hook(@NonNull Observer<T> observer) throws Exception{
            Class<LiveData> classLiveData = LiveData.class;
            Field fieldObservers = classLiveData.getDeclaredField("mObservers");
            fieldObservers.setAccessible(true);
            Object mObservers = fieldObservers.get(this);
            Class<?> classObservers = mObservers.getClass();

            Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
            methodGet.setAccessible(true);

            Object objectWrapperEntry = methodGet.invoke(mObservers, observer);
            Object objectWrapper = ((Map.Entry)objectWrapperEntry).getValue();

            Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();

            Field fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion");
            fieldLastVersion.setAccessible(true);
            Field fieldVersion = classLiveData.getDeclaredField("mVersion");
            fieldVersion.setAccessible(true);

            Object objectVersion = fieldVersion.get(this);
            fieldLastVersion.set(objectWrapper, objectVersion);
        }
    }


}
