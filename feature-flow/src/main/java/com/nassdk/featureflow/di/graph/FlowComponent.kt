package com.nassdk.featureflow.di.graph

import com.nassdk.FlowApi
import com.nassdk.FlowCoreDependencies
import com.nassdk.FlowDependencies
import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featureflow.di.module.FlowModule
import com.nassdk.featureflow.presentation.ui.FlowFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [FlowCoreDependencies::class, FlowDependencies::class],
    modules = [FlowModule::class]
)
internal interface FlowComponent {

    @Component.Factory
    interface Factory {

        fun create(
            flowDependencies: FlowDependencies,
            flowCoreDependencies: FlowCoreDependencies
        ): FlowComponent
    }

    fun inject(fragment: FlowFragment)

    fun moduleApi(): FlowApi
}