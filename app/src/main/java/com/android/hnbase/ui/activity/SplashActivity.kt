package com.android.hnbase.ui.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.android.common.Logger
import com.android.hnbase.R
import com.android.hnbase.base.BaseActivity
import com.android.hnbase.databinding.SplashActivityBinding

class SplashActivity:BaseActivity() {
    var binding:SplashActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        binding = DataBindingUtil.setContentView(this,R.layout.splash_activity)
//        Logger.ihn(TAG,"onCreate()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.splash_activity)
        Logger.ihn(TAG,"onCreate()")
    }

    override fun onResume() {
        super.onResume()

        val splashAnimA = ObjectAnimator.ofFloat(binding?.appNameTv, "alpha", 0f, 1f).setDuration(1000) //透明动画
        val splashAnimAWelcome = ObjectAnimator.ofFloat(binding?.welcomeTv, "alpha", 0f, 1f).setDuration(1000) //透明动画
        val splashAnim = ObjectAnimator.ofFloat(binding?.welcomeTv, "translationY", 300F,0F).setDuration(500) //透明动画
        splashAnimA.start()
        splashAnim.start()
        splashAnimAWelcome.start()
    }
}