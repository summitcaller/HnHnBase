package com.android.hnbase.di

import android.content.Context

interface AppComponents {
    fun getDirector(): Director?
    fun getContext(): Context?
}