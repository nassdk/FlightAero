package com.nassdk.flightaero.mediators

import androidx.annotation.MainThread
import com.nassdk.featureflow.FlowApi
import com.nassdk.featureflow.FlowFeature

class FlowMediator {

    @MainThread
    fun getApi(): FlowApi = FlowFeature.getApi()
}