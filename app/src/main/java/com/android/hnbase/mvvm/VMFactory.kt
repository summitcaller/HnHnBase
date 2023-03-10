package com.example.smallhaique.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.hnbase.mvvm.model.TestModel
import com.android.hnbase.mvvm.viewmodel.TestViewModel

class VMFactory(val application: Application): ViewModelProvider.NewInstanceFactory()  {
    companion object {

        var INSTANCE:VMFactory?=null

        @Synchronized
        fun getInstance(application: Application): VMFactory {
            if (INSTANCE == null) {
                INSTANCE = VMFactory(application)
            }
            return INSTANCE as VMFactory
        }
    }
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestViewModel::class.java)) {
            return TestViewModel(application, TestModel()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}