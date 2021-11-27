package com.nassdk.flightaero.mediators

import com.github.terrakok.modo.android.MultiAppScreen
import com.nassdk.coredi.ModuleDependenciesProvider
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

                        val tabScreens = listOf(
                            MediatorManager.flightsMediator.getApi().provideFlightsScreen(),
                            MediatorManager.profileMediator.getApi().provideProfileScreen()
                        )

                        MediatorManager.flowMediator.getApi().showFlowScreen(
                            screen = MultiAppScreen(
                                id = "MultiApp Screen id",
                                roots = tabScreens,
                                selected = 0
                            )
                        )
                    }
                }
            }
        }
    }
}