package com.nassdk.featureflights

import androidx.annotation.MainThread
import com.github.terrakok.modo.Modo
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent
import com.nassdk.coredi.ModuleDependenciesProvider
import com.nassdk.featureflights.data.network.api.FlightsRestApi
import com.nassdk.featureflights.di.graph.DaggerFlightsComponent
import com.nassdk.featureflights.di.graph.FlightsComponent

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
    fun provideRestApi(): FlightsRestApi
}

interface FlightsCoreDependencies {
    fun exposeModo(): Modo
}

internal class FlightsCoreDependenciesDelegate(
    private val coreComponent: AppComponent,
) : FlightsCoreDependencies {

    override fun exposeModo(): Modo {
        return coreComponent.exposeModo()
    }
}