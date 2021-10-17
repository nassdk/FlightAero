package com.nassdk.featuresplash

import androidx.annotation.MainThread
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent
import com.nassdk.coredi.scopes.ModuleDependenciesProvider
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.featuresplash.di.graph.DaggerSplashComponent
import com.nassdk.featuresplash.di.graph.SplashComponent

object SplashFeature {

    private var component: SplashComponent? = null

    var dependencies: ModuleDependenciesProvider<SplashDependencies>? = null

    @MainThread
    fun getApi(): SplashApi = getComponent().moduleApi()

    internal fun getComponent(): SplashComponent =
        component ?: run {
            component = DaggerSplashComponent.factory()
                .create(
                    requireNotNull(dependencies?.getDependencies()),
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

interface SplashDependencies {
    fun getSplashActions(): SplashActions
}

interface SplashActions {
    fun openFlowScreen()
}

internal class SplashCoreDependenciesDelegate(
    private val coreComponent: AppComponent
) : SplashCoreDependencies {

    override fun exposeGlobalNavigator(): GlobalNavigator {
        return coreComponent.exposeGlobalNavigator()
    }
}