package com.android.hnbase.di.repository

import android.util.Log
import com.android.common.Logger

class UserRepository:Repository {

    val TAG = "UserRepository"

    fun testUser(){
        Logger.ihn(TAG,"testSession()")
    }

}