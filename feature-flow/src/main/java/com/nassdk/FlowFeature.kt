package com.nassdk

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent
import com.nassdk.coredi.scopes.ModuleDependenciesProvider
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.featureflow.di.graph.DaggerFlowComponent
import com.nassdk.featureflow.di.graph.FlowComponent

object FlowFeature {

    private var component: FlowComponent? = null

    var dependencies: ModuleDependenciesProvider<FlowDependencies>? = null

    @MainThread
    fun getApi(): FlowApi = getComponent().moduleApi()

    internal fun getComponent(): FlowComponent =
        component ?: run {
            component = DaggerFlowComponent.factory()
                .create(
                    requireNotNull(dependencies?.getDependencies()),
                    FlowCoreDependenciesDelegate(
                        coreComponent = BaseApplication.app.appComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface FlowDependencies {
    fun provideTabFragments(): TabFragments
}

interface FlowCoreDependencies {
    fun exposeGlobalNavigator(): GlobalNavigator
    fun exposeNavigatorHolder(): NavigatorHolder
}

internal class FlowCoreDependenciesDelegate(
    private val coreComponent: AppComponent
) : FlowCoreDependencies {

    override fun exposeGlobalNavigator(): GlobalNavigator {
        return coreComponent.exposeGlobalNavigator()
    }

    override fun exposeNavigatorHolder(): NavigatorHolder {
        return coreComponent.exposeNavigatorHolder()
    }
}

interface TabFragments {
    fun provideHomeFragment(): Fragment
    fun provideProfileFragment(): Fragment
}