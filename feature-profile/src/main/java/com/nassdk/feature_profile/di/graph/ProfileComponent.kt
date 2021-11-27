package com.nassdk.feature_profile.di.graph

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.feature_profile.ProfileApi
import com.nassdk.feature_profile.di.module.ProfileModule
import com.nassdk.feature_profile.presentation.ProfileFragment
import dagger.Component

@FeatureScope
@Component(modules = [ProfileModule::class])
internal interface ProfileComponent {

    @Component.Factory
    interface Factory {

        fun create(): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)
    fun moduleApi(): ProfileApi
}