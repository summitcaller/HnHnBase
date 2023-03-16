package com.android.hnbase.di

import android.util.Log
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier

class ObjectDirector(val appComponents: AppComponents):Director {
//    private val providers: List<ObjectProvider> = ArrayList<ObjectProvider>()

    var providers= mutableListOf<ObjectProvider>()


    private val TAG = "Director"

    override fun registerProvider(provider: ObjectProvider?) {
        provider?.director = this
        providers.add(provider!!)
    }

    /**
     * 获取类对象
     */
    override fun <T> getInstance(clazz: Class<T>?): T {
        if (providers.isEmpty()) {
            throw NullPointerException("no provider registered!")
        }
        providers.forEach{
            val methods = it.javaClass.declaredMethods
            methods.forEach {
                method ->
                if (method.returnType == clazz && method.modifiers and Modifier.PUBLIC == Modifier.PUBLIC) {
                    try {
                        return method.invoke(it) as T
                    } catch (e: IllegalAccessException) {
                        Log.e(TAG, "create object error, method=$method")
                    } catch (e: InvocationTargetException) {
                        Log.e(TAG, "create object error, method=$method")
                    }
                }
            }
        }
        throw NullPointerException("not find provider")
    }
}