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


        builder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIiLCJlbWFpbCI6IjEyMTIxMjEyMTIiLCJuYmYiOjE3MDU4NDMzOTIsImV4cCI6MTcwNTg0NTE5MiwiaWF0IjoxNzA1ODQzMzkyLCJpc3MiOiJJc3N1ZXIiLCJhdWQiOiJBdWRpZW5jZSJ9.vlEMiNT4VCIOiKyLqwAaTLfM8-c25W8zC9lEWhUpGCrV5XK0O4795sNPInQVl6Cax7ymiQLx1vmamaR6FMFvJg")
//        if(sharedPreferences.token != null) {
//            sharedPreferences.token?.let {
//                builder.addHeader("Authorization", "Bearer $it")
//            }
//        }

        return chain.proceed(builder.build())
    }
}