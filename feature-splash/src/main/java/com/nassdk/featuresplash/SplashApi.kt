package com.nassdk.featuresplash

import com.github.terrakok.modo.android.AppScreen

interface SplashApi {
    /**
     * Feature entry-point
     */
    fun getSplashAppScreen(): AppScreen
}