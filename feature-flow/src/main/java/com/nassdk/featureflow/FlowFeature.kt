package com.nassdk.featureflow

import androidx.annotation.MainThread
import com.github.terrakok.modo.Modo
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent
import com.nassdk.featureflow.di.graph.DaggerFlowComponent
import com.nassdk.featureflow.di.graph.FlowComponent

object FlowFeature {

    private var component: FlowComponent? = null

    @MainThread
    fun getApi(): FlowApi = getComponent().moduleApi()

    internal fun getComponent(): FlowComponent =
        component ?: run {
            component = DaggerFlowComponent.factory()
                .create(
                    FlowCoreDependenciesDelegate(
                        coreComponent = BaseApplication.app.appComponent
                    )
                )
            requireNotNull(component)
        }

    internal fun destroyModuleGraph() {
        component = null
    }
}

internal interface FlowCoreDependencies {
    fun exposeModo(): Modo
}

internal class FlowCoreDependenciesDelegate(
    private val coreComponent: AppComponent,
) : FlowCoreDependencies {

    override fun exposeModo(): Modo {
        return coreComponent.exposeModo()
    }
}