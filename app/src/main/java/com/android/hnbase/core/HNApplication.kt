package com.android.hnbase.core

import android.app.Application
import android.content.Context
import android.util.Log
import com.android.common.Logger
import com.android.hnbase.di.AppComponents
import com.android.hnbase.di.Director
import com.android.hnbase.di.ObjectDirector
import com.android.hnbase.di.modules.RepositoriesProvider

class HNApplication:Application(),AppComponents {

    protected var director: ObjectDirector? = null

    companion object{
        private var INSTANCE:HNApplication? = null
        val TAG = "HNApplication"
        fun getInstance():HNApplication?{
            return INSTANCE
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Logger.ihn(TAG,"attachBaseContext")
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        Logger.ihn(TAG,"onCreate()")
        director = ObjectDirector(this)
        director?.registerProvider(RepositoriesProvider())
    }

    override fun getDirector(): Director? = director

    override fun getContext(): Context = this
}