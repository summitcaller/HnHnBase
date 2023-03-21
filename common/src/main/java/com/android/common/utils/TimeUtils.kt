package com.android.common.utils

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * 时间相关工具类
 * @property TAG String?
 * @property disposable Disposable?
 */
class TimeUtils {

    val TAG = TimeUtils::class.simpleName

    var disposable:Disposable? = null

    /**
     * 倒计时
     * @param timeLen Int 倒计时总时长
     * @param listener CountDownListener 倒计时监听器
     * @param isEvery Boolean 是否每数一次都执行回调
     * @param isCountBack Boolean 是否倒数
     */
    fun countDownSeconds(timeLen:Int, listener: CountDownListener, isEvery:Boolean, isCountBack:Boolean){
        var count = timeLen
        disposable = Observable.interval(1,TimeUnit.SECONDS)
            .subscribe {
                Log.i(TAG,"countDownSeconds it = $it")
                if (isEvery) {
                    if (isCountBack) {
                        listener.onCountDown(count--,null,count == 0)
                    }else{
                        listener.onCountDown(timeLen - (count--),null,count == 0)
                    }
                    if (count == 0) {
                        disposable?.dispose()
                    }
                }else{
                    count--
                    if (count == 0){
                        if (isCountBack) {
                            listener.onCountDown(count,null,count == 0)
                        }else{
                            listener.onCountDown(timeLen,null,count == 0)
                        }
                        disposable?.dispose()
                    }
                }
            }
    }

    /**
     * 重载，默认为不每次执行+倒数
     * @param timeLen Int
     * @param listener CountDownListener
     */
    fun countDownSeconds(timeLen:Int,listener: CountDownListener){
        countDownSeconds(timeLen,listener,false,true)
    }

    /**
     * 倒计时监听器
     */
    interface CountDownListener{

        /**
         * 当倒计时触发式时调用
         * @param timeLen Int 时间值
         * @param thing Any?  附加对象(可选)
         * @param isOver Boolean 是否结束
         */
        fun onCountDown(timeLen:Int,thing:Any?,isOver:Boolean)

    }
}