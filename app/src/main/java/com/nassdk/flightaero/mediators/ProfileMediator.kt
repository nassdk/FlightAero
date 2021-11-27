package com.nassdk.flightaero.mediators

import androidx.annotation.MainThread
import com.nassdk.feature_profile.ProfileApi
import com.nassdk.feature_profile.ProfileFeature

class ProfileMediator {

    @MainThread
    fun getApi(): ProfileApi = ProfileFeature.getApi()
}