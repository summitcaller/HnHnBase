package com.android.hnbase.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.android.hnbase.BR
import com.android.hnbase.R
import com.android.hnbase.base.BaseMvvmActivity
import com.android.hnbase.core.HNApplication
import com.android.hnbase.databinding.TestActivityBinding
import com.android.hnbase.di.repository.UserRepository
import com.android.hnbase.mvvm.viewmodel.TestViewModel
import com.android.hnbase.rxjava.RxjavaHelper
import com.example.smallhaique.viewmodel.factory.VMFactory

class TestActivity:BaseMvvmActivity<TestActivityBinding,TestViewModel>() {

    override fun initView() {
        RxjavaHelper().testDo()
    }

    override fun getBindLayoutId() = R.layout.test_activity

    override fun onBindViewModel() = TestViewModel::class.java

    override fun initData() {
        Log.i(TAG,"initData()")
        HNApplication.getInstance()!!.getDirector()!!.getInstance(UserRepository::class.java).testUser()
    }

    override fun onBindViewModelId() = BR.tesVM

    override fun onBindViewModelFactory(): ViewModelProvider.NewInstanceFactory {
        return VMFactory.getInstance(application)
    }
}