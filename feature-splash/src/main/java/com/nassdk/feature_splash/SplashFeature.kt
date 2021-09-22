package com.nassdk.feature_splash

import androidx.annotation.MainThread
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.feature_splash.di.graph.DaggerSplashComponent
import com.nassdk.feature_splash.di.graph.SplashComponent

object SplashFeature {

    private var component: SplashComponent? = null

    @MainThread
    fun getApi(): SplashApi = getComponent().moduleApi()

    internal fun getComponent(): SplashComponent =
        component ?: run {
            component = DaggerSplashComponent.factory()
                .create(
                    SplashCoreDependenciesDelegate(
                        coreComponent = BaseApplication.app.appComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface SplashCoreDependencies {
    fun exposeGlobalNavigator(): GlobalNavigator
}

internal class SplashCoreDependenciesDelegate(
    private val coreComponent: AppComponent
) : SplashCoreDependencies {

    override fun exposeGlobalNavigator(): GlobalNavigator {
        return coreComponent.exposeGlobalNavigator()
    }
}