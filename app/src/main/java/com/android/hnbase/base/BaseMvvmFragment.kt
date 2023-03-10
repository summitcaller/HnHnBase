package com.android.hnbase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * mvvm fragment
 */
abstract class BaseMvvmFragment <V: ViewDataBinding,VM: BaseViewModel<out BaseModel>>: BaseFragment() {

    /**
     * 绑定类
     */
    protected var binding :V? = null

    protected var viewModel : VM? = null

    private var viewModelId = 0

    /**
     * 获取对应的vm类
     */
    abstract fun onBindViewModel(): Class<VM>

    /**
     * 获取layout id
     */
    abstract fun getBindLayoutId():Int


    /**
     * 获取vm ID
     */
    abstract fun onBindViewModelId(): Int

    /**
     * 初始化view
     */
    abstract fun initView()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getBindLayoutId(),container,false)
        initViewModel()
        return (binding as V).root
    }

    private fun initViewModel(){
        viewModel = createViewModel();
        viewModel?.let { lifecycle.addObserver(it) }
        viewModelId = onBindViewModelId()
        binding?.setVariable(viewModelId,viewModel)
    }

    open fun createViewModel(): VM{
        return ViewModelProvider(this,onBindViewModelFactory()).get(onBindViewModel())
    }

    abstract fun onBindViewModelFactory(): ViewModelProvider.NewInstanceFactory

}