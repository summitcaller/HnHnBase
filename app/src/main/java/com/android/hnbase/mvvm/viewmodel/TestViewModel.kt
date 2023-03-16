package com.android.hnbase.mvvm.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.android.hnbase.base.BaseViewModel
import com.android.hnbase.mvvm.model.TestModel

class TestViewModel(application: Application,model: TestModel):BaseViewModel<TestModel>(application,model) {
    val textLiveData = MutableLiveData<Long>(0)
    private var isFocus = false;
    fun onTest(){
        textLiveData.value = System.currentTimeMillis()
    }

    fun onFocus(view: View){
        Log.i(TAG,"onFocus()")
        doFocusChange(view,!isFocus)
        isFocus = !isFocus
    }
}