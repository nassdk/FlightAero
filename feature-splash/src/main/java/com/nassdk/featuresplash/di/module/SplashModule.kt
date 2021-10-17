package com.nassdk.featuresplash.di.module

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featuresplash.SplashApi
import com.nassdk.featuresplash.SplashApiImpl
import com.nassdk.featuresplash.presentation.navigation.Router
import com.nassdk.featuresplash.presentation.navigation.RouterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface SplashModule {

    @Binds
    @FeatureScope
    fun bindModuleApi(impl: SplashApiImpl): SplashApi

    @Binds
    @FeatureScope
    fun bindRouter(impl: RouterImpl): Router
}