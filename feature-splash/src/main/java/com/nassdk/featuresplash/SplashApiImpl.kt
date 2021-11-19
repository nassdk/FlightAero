package com.nassdk.featuresplash

import com.github.terrakok.modo.android.AppScreen
import com.nassdk.featuresplash.presentation.navigation.SplashScreens
import javax.inject.Inject

internal class SplashApiImpl @Inject constructor() : SplashApi {

    override fun getSplashAppScreen(): AppScreen {
        return SplashScreens.Splash()
    }
}