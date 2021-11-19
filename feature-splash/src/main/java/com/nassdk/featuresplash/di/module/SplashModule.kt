package com.nassdk.featuresplash.di.module

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featuresplash.SplashApi
import com.nassdk.featuresplash.SplashApiImpl
import dagger.Binds
import dagger.Module

@Module
internal interface SplashModule {

    @Binds
    @FeatureScope
    fun bindModuleApi(impl: SplashApiImpl): SplashApi
}