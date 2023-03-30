package com.android.hnbase.ui.activity

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hnbase.BR
import com.android.hnbase.R
import com.android.hnbase.base.BaseMvvmActivity
import com.android.hnbase.core.HNApplication
import com.android.hnbase.databinding.TestActivityBinding
import com.android.hnbase.di.repository.UserRepository
import com.android.hnbase.mvvm.viewmodel.TestViewModel
import com.android.hnbase.rxjava.RxjavaHelper
import com.android.platform.retrofit.RtHelper
import com.example.smallhaique.viewmodel.factory.VMFactory

class TestActivity:BaseMvvmActivity<TestActivityBinding,TestViewModel>() {

    override fun initView() {
        RxjavaHelper().testDo()
        RtHelper().doRt()
    }

    override fun getBindLayoutId() = R.layout.test_activity

    override fun onBindViewModel() = TestViewModel::class.java

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initData() {
        Log.i(TAG,"initData()")
        HNApplication.getInstance()!!.getDirector()!!.getInstance(UserRepository::class.java).testUser()
        viewModel?.startSLD?.observe(this, Observer {
            if (it){
                startS()
            }else{
                stopS()
            }
        })
    }

    override fun onBindViewModelId() = BR.tesVM

    override fun onBindViewModelFactory(): ViewModelProvider.NewInstanceFactory {
        return VMFactory.getInstance(application)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun startS(){
        binding?.searchV?.startView()
    }

    fun stopS(){
        binding?.searchV?.stopView()
    }
}