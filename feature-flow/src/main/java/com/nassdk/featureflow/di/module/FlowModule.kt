package com.nassdk.featureflow.di.module

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featureflow.FlowApi
import com.nassdk.featureflow.FlowApiImpl
import dagger.Binds
import dagger.Module

@Module
internal interface FlowModule {

    @Binds
    @FeatureScope
    fun bindModuleApi(impl: FlowApiImpl): FlowApi
}