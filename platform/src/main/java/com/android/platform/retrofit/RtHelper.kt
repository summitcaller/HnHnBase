package com.android.platform.retrofit

import android.util.Log
import com.android.common.Logger
import com.android.platform.api.GithubService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RtHelper {

    val TAG = javaClass.simpleName

    /**
     * retrofit 官方举例
     */
    fun doRt(){
        val rt = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
        val service: GithubService = rt.create(GithubService::class.java)
        val result:Call<ResponseBody> = service.listRepos("octocat")
        result.enqueue(object :Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Logger.ihn(TAG, "code = ${response.body()?.string()}")
                //这里获取到json字符串以后可以自己解析成对象，也直接将返回值设置成对应的对象来获取。
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }
        })

    }
}