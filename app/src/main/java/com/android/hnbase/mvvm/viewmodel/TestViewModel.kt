package com.android.hnbase.mvvm.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.android.hnbase.base.BaseViewModel
import com.android.hnbase.mvvm.model.TestModel


class TestViewModel(application: Application,model: TestModel):BaseViewModel<TestModel>(application,model) {
    val textLiveData = MutableLiveData<Long>(0)
    val startSLD :MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    private var isFocus = false;
    fun onTest(){
        textLiveData.value = System.currentTimeMillis()
    }

    fun onFocus(view: View){
        Log.i(TAG,"onFocus()")
        doFocusChange(view,!isFocus)
        isFocus = !isFocus
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.i(TAG,"lifecycle onCreate()")
    }

    fun startSearch(){
        startSLD.value = true
    }

    fun stopSearch(){
        startSLD.value = false
    }

    fun toFlutter(){
        val intent: Intent = FlutterActivity.withNewEngine()
            .initialRoute("myFlutterRoute") // 这里指定Flutter页面的路由名
            .build(this)
    }
}