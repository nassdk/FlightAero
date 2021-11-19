package com.nassdk.flightaero.mediators

import androidx.annotation.MainThread
import com.nassdk.corenetwork.NetworkApi
import com.nassdk.corenetwork.NetworkWrapper

class NetworkMediator {

    @MainThread
    fun getApi(): NetworkApi = NetworkWrapper.getApi()
}