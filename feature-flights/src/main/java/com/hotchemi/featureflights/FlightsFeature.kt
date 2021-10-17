package com.hotchemi.featureflights

import androidx.annotation.MainThread
import com.hotchemi.featureflights.data.network.api.RestApi
import com.hotchemi.featureflights.di.graph.DaggerFlightsComponent
import com.hotchemi.featureflights.di.graph.FlightsComponent
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent
import com.nassdk.coredi.scopes.ModuleDependenciesProvider
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.corenavigation.handler.LocalCiceroneHolder

object FlightsFeature {

    private var component: FlightsComponent? = null

    var dependencies: ModuleDependenciesProvider<FlightsDependencies>? = null

    @MainThread
    fun getApi(): FlightsApi = getComponent().moduleApi()

    internal fun getComponent(): FlightsComponent =
        component ?: run {
            component = DaggerFlightsComponent.factory()
                .create(
                    requireNotNull(dependencies?.getDependencies()),
                    FlightsCoreDependenciesDelegate(
                        coreComponent = BaseApplication.app.appComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

interface FlightsDependencies {
    fun provideRestApi(): RestApi
}

interface FlightsCoreDependencies {
    fun exposeGlobalNavigator(): GlobalNavigator
    fun exposeLocalCiceroneHolder(): LocalCiceroneHolder
}

internal class FlightsCoreDependenciesDelegate(
    private val coreComponent: AppComponent
) : FlightsCoreDependencies {

    override fun exposeGlobalNavigator(): GlobalNavigator {
        return coreComponent.exposeGlobalNavigator()
    }

    override fun exposeLocalCiceroneHolder(): LocalCiceroneHolder {
        return coreComponent.exposeLocalCiceroneHolder()
    }
}