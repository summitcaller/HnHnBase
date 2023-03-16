package com.android.hnbase.di.modules

import com.android.hnbase.di.ObjectProvider
import com.android.hnbase.di.repository.SessionRepository
import com.android.hnbase.di.repository.UserRepository

class RepositoriesProvider:ObjectProvider() {

    public fun getUserRepositories():UserRepository = UserRepository() //新建模式

    fun getSessionRepository():SessionRepository = SessionSingleRepository.single //单例模式

    object SessionSingleRepository{
        val single = SessionRepository()
    }

}