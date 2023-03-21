package com.android.hnbase.ui.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.android.common.Logger
import com.android.common.utils.TimeUtils
import com.android.hnbase.R
import com.android.hnbase.base.BaseActivity
import com.android.hnbase.databinding.SplashActivityBinding

class SplashActivity:BaseActivity() {
    var binding:SplashActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) { //这个方法正常的生命周期并不会执行
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.splash_activity)
        Logger.ihn(TAG,"onCreate()")
        val splashAnimA = ObjectAnimator.ofFloat(binding?.appNameTv, "alpha", 0f, 1f).setDuration(1000) //透明动画
        val splashAnimAWelcome = ObjectAnimator.ofFloat(binding?.welcomeTv, "alpha", 0f, 1f).setDuration(1000) //透明动画
        val splashAnim = ObjectAnimator.ofFloat(binding?.welcomeTv, "translationY", 300F,0F).setDuration(1000) //位移动画
        splashAnimA.start()
        splashAnim.start()
        splashAnimAWelcome.start()
        TimeUtils().countDownSeconds(10,object:TimeUtils.CountDownListener{
            override fun onCountDown(timeLen: Int, thing: Any?, isOver:Boolean) {
                Logger.ihn(TAG,"timeLen = $timeLen")
                runOnUiThread {
                    binding!!.nextButton.text = "($timeLen"+"S) "+ resources.getString(R.string.next)
                    if (isOver) {
                        toOther()
                    }
                }
            }
        },true,true)
        binding!!.nextButton.text = "(10S) "+ resources.getString(R.string.next)
    }

    override fun onResume() {
        super.onResume()
    }

    fun toOther(){
        Logger.ihn(TAG,"toOther()")
        val intent = Intent(this,TestActivity::class.java)
        startActivity(intent)
        finish()
    }
}