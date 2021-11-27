package com.nassdk.feature_profile

import androidx.annotation.MainThread
import com.nassdk.feature_profile.di.graph.DaggerProfileComponent
import com.nassdk.feature_profile.di.graph.ProfileComponent

object ProfileFeature {

    private var component: ProfileComponent? = null

    @MainThread
    fun getApi(): ProfileApi = getComponent().moduleApi()

    internal fun getComponent(): ProfileComponent =
        component ?: run {
            component = DaggerProfileComponent.factory().create()
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}