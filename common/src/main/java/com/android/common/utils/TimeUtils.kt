package com.android.common.utils

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class TimeUtils {
    val TAG = TimeUtils::class.simpleName
    var disposable:Disposable? = null
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

    fun countDownSeconds(timeLen:Int,listener: CountDownListener){
        countDownSeconds(timeLen,listener,false,true)
    }

    interface CountDownListener{
        fun onCountDown(timeLen:Int,thing:Any?,isOver:Boolean)
    }
}