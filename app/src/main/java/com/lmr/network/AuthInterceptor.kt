package com.lmr.network

import com.lmr.app_utils.AppSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sharedPreferences: AppSharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainRequest = chain.request()
        val builder = mainRequest.newBuilder()
            .method(mainRequest.method, mainRequest.body)


        builder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIiLCJlbWFpbCI6IjQzIiwibmJmIjoxNzMzMzMxODk4LCJleHAiOjE3MzMzMzM2OTgsImlhdCI6MTczMzMzMTg5OCwiaXNzIjoiSXNzdWVyIiwiYXVkIjoiQXVkaWVuY2UifQ.yjpDRYUnIw6pwN9--NpBvHbTjmfrxab7t7BJEV6S3-c32-kLCJm0II6aVrg02tv6repCV_62l66VQLeFJOawGA")
//        if(sharedPreferences.token != null) {
//            sharedPreferences.token?.let {
//                builder.addHeader("Authorization", "Bearer $it")
//            }
//        }

        return chain.proceed(builder.build())
    }
}