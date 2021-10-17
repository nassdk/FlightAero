package com.hotchemi.featureflights.di.graph

import com.hotchemi.featureflights.FlightsApi
import com.hotchemi.featureflights.FlightsCoreDependencies
import com.hotchemi.featureflights.FlightsDependencies
import com.hotchemi.featureflights.di.module.FlightsModule
import com.hotchemi.featureflights.presentation.ui.FlightsFragment
import com.nassdk.coredi.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [FlightsCoreDependencies::class, FlightsDependencies::class],
    modules = [FlightsModule::class]
)
internal interface FlightsComponent {

    @Component.Factory
    interface Factory {

        fun create(
            flowDependencies: FlightsDependencies,
            flowCoreDependencies: FlightsCoreDependencies
        ): FlightsComponent
    }

    fun inject(fragment: FlightsFragment)

    fun moduleApi(): FlightsApi
}