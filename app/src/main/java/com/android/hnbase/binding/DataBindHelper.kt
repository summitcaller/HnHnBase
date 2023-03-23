package com.android.hnbase.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.android.common.Logger

/**
 * author:aclidae on 2023/3/21 18 45
 * des: dataBinding 辅助类
 */
class DataBindHelper {
    companion object{
        const val TAG = "MainViewModel_plugin_tv"

        /**
         * 绑定属性 isRequestFocus，函数的第一个参数类型规定了哪些view可以使用这个标签
         * @param v View
         * @param isRequestFocus Boolean
         */
        @JvmStatic
        @BindingAdapter("isRequestFocus")
        fun setFocus(v: View, isRequestFocus:Boolean){
            Logger.ihn(TAG,"setFocus $isRequestFocus")
            if (isRequestFocus) {
                v.requestFocusFromTouch()
            }
        }
    }
}