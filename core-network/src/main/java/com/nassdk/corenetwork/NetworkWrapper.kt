package com.nassdk.corenetwork

import com.nassdk.corenetwork.di.DaggerNetworkComponent
import com.nassdk.corenetwork.di.NetworkComponent

object NetworkWrapper {

    private var component: NetworkComponent? = null

    fun getApi(): NetworkApi = getComponent().moduleApi()

    internal fun getComponent(): NetworkComponent =
        component ?: run {
            component = DaggerNetworkComponent.factory()
                .create()
            requireNotNull(component)
        }
}