package com.nassdk.corenetwork

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal class NetworkApiImpl @Inject constructor() : NetworkApi {

    init {
        NetworkWrapper.getComponent().inject(this)
    }

    @Inject lateinit var json: Json
    @Inject lateinit var okhttp3Client: OkHttpClient

    override fun <T> provideApiClass(interfaceClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp3Client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(interfaceClass)
    }

    private companion object {
        private const val BASE_URL = "http://api.aviationstack.com/v1/"
    }
}