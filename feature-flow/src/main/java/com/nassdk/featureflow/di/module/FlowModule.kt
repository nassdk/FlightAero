package com.nassdk.featureflow.di.module

import com.nassdk.FlowApi
import com.nassdk.FlowApiImpl
import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featureflow.presentation.navigation.Router
import com.nassdk.featureflow.presentation.navigation.RouterImpl
import dagger.Binds
import dagger.Module

@Module
internal interface FlowModule {

    @Binds
    @FeatureScope
    fun bindRouter(impl: RouterImpl): Router

    @Binds
    @FeatureScope
    fun bindModuleApi(impl: FlowApiImpl): FlowApi
}