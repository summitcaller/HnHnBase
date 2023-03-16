package com.android.hnbase.di

interface Director {
    fun registerProvider(provider: ObjectProvider?)

    fun <T> getInstance(clazz: Class<T>?): T
}