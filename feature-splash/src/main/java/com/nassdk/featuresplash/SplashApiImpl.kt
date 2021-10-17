package com.nassdk.featuresplash

import com.nassdk.featuresplash.presentation.navigation.Router
import javax.inject.Inject

internal class SplashApiImpl @Inject constructor(
    private val router: Router
) : SplashApi {
    override fun showSplashScreen() = router.openSplashScreen()
}