package com.nassdk.corenetwork.interceptor

import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@Reusable
class AppInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}