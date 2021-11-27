package com.nassdk.feature_profile.di.module

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.feature_profile.ProfileApi
import com.nassdk.feature_profile.ProfileApiImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ProfileModule {

    @Binds
    @FeatureScope
    fun bindModuleApi(impl: ProfileApiImpl): ProfileApi
}