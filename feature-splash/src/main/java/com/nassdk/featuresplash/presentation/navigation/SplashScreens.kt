package com.nassdk.featuresplash.presentation.navigation

import android.annotation.SuppressLint
import com.github.terrakok.modo.android.AppScreen
import com.nassdk.featuresplash.presentation.ui.SplashFragment
import kotlinx.parcelize.Parcelize

@SuppressLint("CustomSplashScreen")
internal object SplashScreens {

    @Parcelize
    class Splash : AppScreen("Splash") {
        override fun create() = SplashFragment.newInstance()
    }
}