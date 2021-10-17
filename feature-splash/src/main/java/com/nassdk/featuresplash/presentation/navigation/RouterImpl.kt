package com.nassdk.featuresplash.presentation.navigation

import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import javax.inject.Inject

internal class RouterImpl @Inject constructor(
    private val navigator: GlobalNavigator
) : Router {
    override fun openSplashScreen() = navigator.newRootFragment(SplashScreens.Splash())
}