package com.nassdk.featureflow

import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiScreen
import com.github.terrakok.modo.android.MultiStackFragment
import com.github.terrakok.modo.replace
import com.nassdk.featureflow.presentation.ui.FlowFragment
import javax.inject.Inject

internal class FlowApiImpl @Inject constructor(
    private val modo: Modo,
) : FlowApi {

    override fun showFlowScreen(screen: MultiScreen) = modo.replace(screen = screen)

    override fun getFlowAsMultistackFragment(): MultiStackFragment {
        return FlowFragment.newInstance()
    }
}