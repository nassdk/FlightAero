package com.nassdk.feature_splash.di.graph

import com.nassdk.feature_splash.SplashCoreDependencies
import com.nassdk.feature_splash.di.module.SplashModule
import com.nassdk.feature_splash.presentation.ui.SplashFragment
import dagger.Component

@Component(dependencies = [SplashCoreDependencies::class], modules = [SplashModule::class])
internal interface SplashComponent {

    @Component.Factory
    interface Factory {

        fun create(splashCoreDependencies: SplashCoreDependencies): SplashComponent
    }

    fun inject(fragment: SplashFragment)
}