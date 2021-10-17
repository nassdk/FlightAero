package com.nassdk.featuresplash.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.featuresplash.presentation.ui.SplashFragment

internal object SplashScreens {

    fun Splash() = FragmentScreen {
        SplashFragment.newInstance()
    }
}