package com.android.hnbase.di

import android.util.Log
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Modifier

/**
 * 这个类提供的主要功能是通过类名来获取类对象实例。
 * 核心逻辑是保存一个注册工厂的list，当要获取一个类的对象时，遍历所有的工厂以及其方法，得到返回类型与所需对象所属类相同的方法（public）并执行来获取指定的类对象。
 */
class ObjectDirector(val appComponents: AppComponents):Director {
//    private val providers: List<ObjectProvider> = ArrayList<ObjectProvider>()

    var providers= mutableListOf<ObjectProvider>()


    private val TAG = "Director"

    /**
     * 需要注意的是所有注册的类中不能有多个public方法返回相同的类型
     */
    override fun registerProvider(provider: ObjectProvider?) {
        provider?.director = this
        providers.add(provider!!)
    }

    /**
     * 获取类对象，需要注意的是所有注册的类中不能有多个public方法返回相同的类型
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