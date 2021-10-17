package com.nassdk.featureflow.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.featureflow.presentation.ui.FlowFragment

internal object FlowScreens {

    fun Flow() = FragmentScreen {
        FlowFragment.newInstance()
    }
}