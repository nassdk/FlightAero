package com.nassdk.featureflow.presentation.navigation

import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import javax.inject.Inject

internal class RouterImpl @Inject constructor(
    private val globalNavigator: GlobalNavigator
) : Router {

    override fun openFlowScreen() {
        globalNavigator.newRootFragment(FlowScreens.Flow())
    }
}