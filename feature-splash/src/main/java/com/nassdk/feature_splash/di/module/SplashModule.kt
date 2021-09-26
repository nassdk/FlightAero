package com.nassdk.feature_splash.di.module

import com.nassdk.feature_splash.presentation.navigation.Router
import com.nassdk.feature_splash.presentation.navigation.RouterImpl
import dagger.Binds
import dagger.Module

@Module
interface SplashModule {

    @Binds
    fun bindRouter(impl: RouterImpl): Router
}