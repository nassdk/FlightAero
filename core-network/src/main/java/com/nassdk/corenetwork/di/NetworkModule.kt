package com.nassdk.corenetwork.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nassdk.corenetwork.BuildConfig
import com.nassdk.corenetwork.interceptor.AppInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun provideOkHttp3(
        appInterceptor: AppInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) addInterceptor(appInterceptor)
    }.build()


    @Provides
    @Reusable
    fun provideJson(): Json {
        return Json(Json.Default) {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Reusable
    fun provideRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.vk.com/method/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

}