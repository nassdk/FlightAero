package com.nassdk.featuresplash.di.graph

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featuresplash.SplashApi
import com.nassdk.featuresplash.SplashCoreDependencies
import com.nassdk.featuresplash.SplashDependencies
import com.nassdk.featuresplash.di.module.SplashModule
import com.nassdk.featuresplash.presentation.ui.SplashFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [SplashCoreDependencies::class, SplashDependencies::class],
    modules = [SplashModule::class]
)
internal interface SplashComponent {

    @Component.Factory
    interface Factory {

        fun create(
            splashDependencies: SplashDependencies,
            splashCoreDependencies: SplashCoreDependencies
        ): SplashComponent
    }

    fun inject(fragment: SplashFragment)

    fun moduleApi(): SplashApi
}