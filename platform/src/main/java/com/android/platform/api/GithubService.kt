package com.android.platform.api

import com.android.platform.entity.Repo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<ResponseBody>
}