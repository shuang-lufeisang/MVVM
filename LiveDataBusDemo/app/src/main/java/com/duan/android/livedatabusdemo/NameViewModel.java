package com.duan.android.livedatabusdemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * <pre>
 * author : Duan
 * time : 2019/07/01
 * desc :
 * version: 1.0
 * </pre>
 */
public class NameViewModel extends ViewModel {

    private MutableLiveData<String> mCurrentName;
    public MutableLiveData<String> getCurrentName(){
        if (mCurrentName == null){
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }
}
