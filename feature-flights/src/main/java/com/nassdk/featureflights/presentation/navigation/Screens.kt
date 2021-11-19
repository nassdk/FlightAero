package com.nassdk.featureflights.presentation.navigation

import com.github.terrakok.modo.android.AppScreen
import com.nassdk.featureflights.presentation.ui.FlightsFragment
import kotlinx.parcelize.Parcelize

internal object Screens {

    @Parcelize
    class Flights : AppScreen("Flights") {
        override fun create() = FlightsFragment.newInstance()
    }
}