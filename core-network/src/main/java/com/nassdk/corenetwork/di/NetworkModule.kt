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
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Reusable
    fun provideOkHttp3(
        appInterceptor: AppInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(appInterceptor)
            addInterceptor(loggingInterceptor)
        }
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