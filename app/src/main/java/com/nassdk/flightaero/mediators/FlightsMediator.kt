package com.nassdk.flightaero.mediators

import androidx.annotation.MainThread
import com.nassdk.coredi.ModuleDependenciesProvider
import com.nassdk.featureflights.FlightsApi
import com.nassdk.featureflights.FlightsDependencies
import com.nassdk.featureflights.FlightsFeature
import com.nassdk.featureflights.data.network.api.FlightsRestApi

class FlightsMediator {

    init {
        FlightsFeature.dependencies = ModuleDependenciesProvider { createDependencies() }
    }

    @MainThread
    fun getApi(): FlightsApi = FlightsFeature.getApi()

    private fun createDependencies(): FlightsDependencies {
        return object : FlightsDependencies {
            override fun provideRestApi(): FlightsRestApi {
                return MediatorManager.networkMediator
                    .getApi()
                    .provideApiClass(FlightsRestApi::class.java)
            }
        }
    }
}