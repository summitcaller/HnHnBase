package com.android.hnbase.base

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.common.Logger

/**
 * Base activity
 */
open class BaseActivity:AppCompatActivity(){
    val TAG = this::class.java.simpleName
    var isInteralBack = false
    var isLeaveListener = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
        } catch (e: Exception) {
            Logger.ehn(TAG, "onCreate, failed hiding title bar")
        }
    }

    /**
     * 用户手动离开当前activity，会调用该方法，比如用户主动切换任务，短按home进入桌面等。系统自动切换activity不会调用此方法，如来电，灭屏等。
     */
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (!isLeaveListener) {
            return
        }
        Logger.ihn(TAG,"onUserLeaveHint()")
        Toast.makeText(this,"应用进入后台运行!",Toast.LENGTH_LONG).show()
    }

    /**
     * 每当Key，Touch,Trackball事件分发到当前Activity就会被调用。如果你想当你的Activity在运行的时候，能够得知用户正在与你的设备交互，你可以override该方法。
     */
    override fun onUserInteraction() {
        super.onUserInteraction()
        Logger.ihn(TAG,"onUserInteraction()")
    }

    override fun onBackPressed() {
        if (isInteralBack){
            showDialog()
        }else{
            super.onBackPressed()
        }
    }

    open fun showDialog(){

    }

}