package com.nassdk.flightaero.mediators

import com.nassdk.coredi.scopes.ModuleDependenciesProvider
import com.nassdk.featuresplash.SplashActions
import com.nassdk.featuresplash.SplashApi
import com.nassdk.featuresplash.SplashDependencies
import com.nassdk.featuresplash.SplashFeature

class SplashMediator {

    init {
        SplashFeature.dependencies = ModuleDependenciesProvider { createDependencies() }
    }

    fun getApi(): SplashApi = SplashFeature.getApi()

    private fun createDependencies(): SplashDependencies {
        return object : SplashDependencies {
            override fun getSplashActions(): SplashActions {
                return object : SplashActions {
                    override fun openFlowScreen() {
                        MediatorManager.flowMediator.getApi().showFlowScreen()
                    }
                }
            }
        }
    }
}