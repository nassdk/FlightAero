package com.nassdk

import com.nassdk.featureflow.presentation.navigation.Router
import javax.inject.Inject

internal class FlowApiImpl @Inject constructor(
    private val router: Router
) : FlowApi {

    override fun showFlowScreen() = router.openFlowScreen()
}