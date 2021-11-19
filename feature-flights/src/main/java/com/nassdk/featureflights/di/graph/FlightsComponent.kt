package com.nassdk.featureflights.di.graph

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featureflights.FlightsApi
import com.nassdk.featureflights.FlightsCoreDependencies
import com.nassdk.featureflights.FlightsDependencies
import com.nassdk.featureflights.di.module.FlightsModule
import com.nassdk.featureflights.presentation.ui.FlightsFragment
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
            flowCoreDependencies: FlightsCoreDependencies,
        ): FlightsComponent
    }

    fun inject(fragment: FlightsFragment)

    fun moduleApi(): FlightsApi
}