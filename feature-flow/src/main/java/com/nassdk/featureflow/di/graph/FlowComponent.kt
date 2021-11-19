package com.nassdk.featureflow.di.graph

import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featureflow.FlowApi
import com.nassdk.featureflow.FlowCoreDependencies
import com.nassdk.featureflow.di.module.FlowModule
import com.nassdk.featureflow.presentation.ui.FlowFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [FlowCoreDependencies::class],
    modules = [FlowModule::class]
)
internal interface FlowComponent {

    @Component.Factory
    interface Factory {

        fun create(
            flowCoreDependencies: FlowCoreDependencies,
        ): FlowComponent
    }

    fun inject(fragment: FlowFragment)

    fun moduleApi(): FlowApi
}