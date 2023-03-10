package com.android.hnbase.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver

open class BaseViewModel<M: BaseModel>(application: Application, val model:M) :AndroidViewModel(application), LifecycleObserver{
    val TAG = this::class.simpleName + "_plugin_tv"
}