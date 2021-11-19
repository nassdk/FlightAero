package com.nassdk.featureflow

import com.github.terrakok.modo.MultiScreen
import com.github.terrakok.modo.android.MultiStackFragment

interface FlowApi {

    /**
     * Feature entry-point
     */
    fun showFlowScreen(screen: MultiScreen)

    fun getFlowAsMultistackFragment(): MultiStackFragment
}