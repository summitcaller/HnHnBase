package com.android.hnbase.base

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseMvvmActivity<V:ViewDataBinding,VM: BaseViewModel<out BaseModel>>: BaseActivity(){
    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 获取layout id
     */
    abstract fun getBindLayoutId():Int

    /**
     * 获取对应的vm类
     */
    abstract fun onBindViewModel(): Class<VM>

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 获取vm ID
     */
    abstract fun onBindViewModelId(): Int

    /**
     * 获取 vm factory
     */
    abstract fun onBindViewModelFactory(): ViewModelProvider.NewInstanceFactory

    /**
     * 绑定类
     */
    protected var binding :V? = null

    protected var viewModel : VM? = null

    /**
     * vm id 在自动生成的BR类中获取
     */
    private var viewModelId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding()
    }

    open fun createViewModel(): VM{
        return ViewModelProvider(this,onBindViewModelFactory()).get(onBindViewModel())
    }

    private fun initViewDataBinding(){
        Log.i(TAG,"initViewDataBinding()")
        binding = DataBindingUtil.setContentView(this,getBindLayoutId())
        binding?.lifecycleOwner = this
        viewModel = createViewModel();
        viewModel?.let { lifecycle.addObserver(it) }
        viewModelId = onBindViewModelId()
        binding?.setVariable(viewModelId,viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
    }
}