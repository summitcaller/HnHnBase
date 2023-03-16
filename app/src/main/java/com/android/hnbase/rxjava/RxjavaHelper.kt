package com.android.hnbase.rxjava

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.*
import java.util.concurrent.TimeUnit


class RxjavaHelper {
    val TAG = "RxjavaHelper"
    fun test(){
//        Observable.combineLatest(Observable.just(1L),Observable.just(1L), (BiFunction<Long, Long, String> { t, u -> "$t and $u" } ))

//        Observable.combineLatest(
//            Observable.interval(1, TimeUnit.SECONDS),
//            Observable.interval(2, TimeUnit.SECONDS)
//        ) { t1, t2 -> "$t1  $t2" }
//            .take(10)
//            .subscribe(object : Observer<String> {
//                override fun onSubscribe(d: Disposable) {
//                    Log.e(TAG, "---------onSubscribe---------")
//                }
//                override fun onNext(t: String) {
//                    Log.e(TAG, "---------onNext--------${t}-")
//                }
//                override fun onError(e: Throwable) {
//                    Log.e(TAG, "---------onError---------")
//                }
//                override fun onComplete() {
//                    Log.e(TAG, "---------onComplete---------")
//                }
//            })

        Observable.combineLatest(
            Observable.interval(1, TimeUnit.SECONDS),
            Observable.interval(2, TimeUnit.SECONDS)
        ) { t1, t2 -> "$t1  $t2" }
//            .take(10)
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "---------onSubscribe---------")
                }
                override fun onNext(t: String) {
                    Log.e(TAG, "---------onNext--------${t}")
                }
                override fun onError(e: Throwable) {
                    Log.e(TAG, "---------onError---------")
                }
                override fun onComplete() {
                    Log.e(TAG, "---------onComplete---------")
                }
            })
    }


    fun testMerge(){
        Observable.merge(
            Observable.interval(3, TimeUnit.SECONDS)
                .flatMap {
                    Observable.just("第一个 $it")
                }.take(5),
            Observable.interval(1, TimeUnit.SECONDS)
                .flatMap {
                    Observable.just("第二个 $it")
                }.take(5)
        ).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "---------onSubscribe---------")
            }
            override fun onNext(t: String) {
                Log.e(TAG, "---------onNext--------${t}-")
            }
            override fun onError(e: Throwable) {
                Log.e(TAG, "---------onError---------")
            }
            override fun onComplete() {
                Log.e(TAG, "---------onComplete---------")
            }
        })
    }

    fun testStartWith(){
        Observable.just(2,3)
            .startWith(Observable.just(1))
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "---------onSubscribe---------")
                }
                override fun onNext(t: Int) {
                    Log.e(TAG, "---------onNext--------${t}-")
                }
                override fun onError(e: Throwable) {
                    Log.e(TAG, "---------onError---------")
                }
                override fun onComplete() {
                    Log.e(TAG, "---------onComplete---------")
                }
            })
    }

    fun testZip(){
        val take1 = Observable.interval(2, TimeUnit.SECONDS)
            .flatMap {
                Observable.just("第一个 $it")
            }.take(5)

        val take2 = Observable.interval(1, TimeUnit.SECONDS)
            .flatMap {
                Observable.just("第二个 $it")
            }.take(5).filter{it.contains("第")}

        Observable.zip(take1, take2, object : BiFunction<String, String, String> {
            override fun apply(t1: String, t2: String): String {
                return "$t1-----------$t2"
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "---------onSubscribe---------")
            }
            override fun onNext(t: String) {
                Log.e(TAG, "---------onNext--------${t}-")
            }
            override fun onError(e: Throwable) {
                Log.e(TAG, "---------onError---------")
            }
            override fun onComplete() {
                Log.e(TAG, "---------onComplete---------")
            }
        })
    }

    fun testDo(){
        Observable.just(1, 2, 3)
            .doOnEach {

                Log.e(TAG, "---------doOnEach-------${it.value}--")
            }
            .doOnNext {
                Log.e(TAG, "---------doOnNext-------${it}--")
            }
            .doOnSubscribe {
                Log.e(TAG, "---------doOnSubscribe--------")
            }
            .doOnComplete {
                Log.e(TAG, "---------doOnComplete--------")
            }
            .doOnError {
                Log.e(TAG, "---------doOnError--------")
            }
            .doOnTerminate {
                Log.e(TAG, "---------doOnTerminate--------")
            }
            .doFinally {
                Log.e(TAG, "---------doFinally--------")
            }.doOnDispose{
                Log.e(TAG, "---------doOnDispose--------")
            }
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    Log.e(TAG, "---------onSubscribe---------")
                    Observable.just(d).subscribe{it.dispose()}
                }
                override fun onNext(t: Int) {
                    Log.e(TAG, "---------onNext--------${t}-")
                }
                override fun onError(e: Throwable) {
                    Log.e(TAG, "---------onError---------")
                }
                override fun onComplete() {
                    Log.e(TAG, "---------onComplete---------")
                }
            })
    }
    var disposable:Disposable? = null
}