package com.nassdk.featureflights

import com.github.terrakok.modo.android.AppScreen
import com.nassdk.featureflights.presentation.navigation.Screens
import javax.inject.Inject

internal class FlightsApiImpl @Inject constructor() : FlightsApi {

    override fun provideFlightsScreen(): AppScreen {
        return Screens.Flights()
    }
}