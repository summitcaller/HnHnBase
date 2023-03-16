package com.android.common

import android.util.Log

class Logger {
    companion object{
        const val TAG = "_LHNDemo"
        fun ihn(tag:String,message:String) = run { Log.i(tag + TAG,message) }
        fun dhn(tag:String,message:String) = run { Log.d(tag + TAG,message) }
        fun ehn(tag:String,message:String) = run { Log.e(tag + TAG,message) }
        fun whn(tag:String,message:String) = run { Log.w(tag + TAG,message) }
    }
}