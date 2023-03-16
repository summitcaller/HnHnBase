package com.android.hnbase.base

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver

open class BaseViewModel<M: BaseModel>(application: Application, val model:M) :AndroidViewModel(application), LifecycleObserver{
    val TAG = this::class.simpleName + "_plugin_tv"

    open fun doFocusChange(view: View, hasFocus: Boolean) {
        Log.i(TAG,"doFocusChange $hasFocus")
        val startY: Float
        val endY: Float
        val startX: Float
        val endX: Float
        if (hasFocus) {
            startY = 1.0f
            endY = 1.1f
            startX = 1.0f
            endX = 1.0f
        } else {
            startY = 1.1f
            endY = 1.0f
            startX = 1.0f
            endX = 1.0f
        }
        val scaleX: ObjectAnimator = getObjectAnimator(view, startY, endY, startX, endX)
        scaleX.start()
    }


    open fun getObjectAnimator(
        view: View,
        startY: Float,
        endY: Float,
        startX: Float,
        endX: Float
    ): ObjectAnimator {
        //会造成焦点选中边框无法全覆盖,所以x轴不放大
        val apvhY = PropertyValuesHolder.ofFloat("scaleX", startX, endX)
        val apvhZ = PropertyValuesHolder.ofFloat("scaleY", startY, endY)
        return ObjectAnimator.ofPropertyValuesHolder(view, apvhY, apvhZ).setDuration(200)
    }
}