package com.android.hnbase.di

abstract class ObjectProvider {

    //late init field, initialize by director when register.
    var director: Director? = null
}