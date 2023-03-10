package com.android.hnbase.ui.activity

import androidx.lifecycle.ViewModelProvider
import com.android.hnbase.BR
import com.android.hnbase.R
import com.android.hnbase.base.BaseMvvmActivity
import com.android.hnbase.databinding.TestActivityBinding
import com.android.hnbase.mvvm.viewmodel.TestViewModel
import com.example.smallhaique.viewmodel.factory.VMFactory

class TestActivity:BaseMvvmActivity<TestActivityBinding,TestViewModel>() {

    override fun initView() {
        //todo
    }

    override fun getBindLayoutId() = R.layout.test_activity

    override fun onBindViewModel() = TestViewModel::class.java


    override fun initData() {
        //todo
    }

    override fun onBindViewModelId() = BR.tesVM

    override fun onBindViewModelFactory(): ViewModelProvider.NewInstanceFactory {
        return VMFactory.getInstance(application)
    }
}