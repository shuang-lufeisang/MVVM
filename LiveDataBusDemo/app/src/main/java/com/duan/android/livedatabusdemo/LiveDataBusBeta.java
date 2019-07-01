package com.duan.android.livedatabusdemo;

import android.arch.lifecycle.MutableLiveData;

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
public class LiveDataBusBeta {

    private final Map<String, MutableLiveData<Object>> bus;

    public LiveDataBusBeta() {
        this.bus = new HashMap<>();
    }

    private static class SingleHolder{
        private static final LiveDataBusBeta DATA_BUS = new LiveDataBusBeta();
    }

    public static LiveDataBusBeta get(){
        return SingleHolder.DATA_BUS;
    }

    public <T> MutableLiveData<T> with(String target,Class<T> type){
        if (!bus.containsKey(target)){
            bus.put(target,new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(target);
    }

    public  MutableLiveData<Object> with(String target){
        return with(target, Object.class);
    }
}
