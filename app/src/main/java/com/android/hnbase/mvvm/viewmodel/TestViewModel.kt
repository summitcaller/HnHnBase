package com.android.hnbase.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.hnbase.base.BaseViewModel
import com.android.hnbase.mvvm.model.TestModel

class TestViewModel(application: Application,model: TestModel):BaseViewModel<TestModel>(application,model) {
    val textLiveData = MutableLiveData<Long>(0)

    fun onTest(){
        textLiveData.value = System.currentTimeMillis()
    }
}