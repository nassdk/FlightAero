package com.nassdk.flightaero.mediators

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import com.nassdk.FlowApi
import com.nassdk.FlowDependencies
import com.nassdk.FlowFeature
import com.nassdk.TabFragments
import com.nassdk.coredi.scopes.ModuleDependenciesProvider

class FlowMediator {

    init {
        FlowFeature.dependencies = ModuleDependenciesProvider { createDependencies() }
    }

    @MainThread
    fun getApi(): FlowApi = FlowFeature.getApi()

    private fun createDependencies(): FlowDependencies {
        return object : FlowDependencies {
            override fun provideTabFragments() = tabsFragments()
        }
    }

    private fun tabsFragments(): TabFragments =
        object : TabFragments {
            override fun provideHomeFragment(): Fragment {
                return SplashFragment()
            }

            override fun provideProfileFragment(): Fragment {
                return SplashFragment()
            }
        }
}