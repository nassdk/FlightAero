package com.nassdk.feature_profile

import com.github.terrakok.modo.android.AppScreen
import com.nassdk.feature_profile.presentation.navigation.Screens
import javax.inject.Inject

internal class ProfileApiImpl @Inject constructor() : ProfileApi {

    override fun provideProfileScreen(): AppScreen {
        return Screens.Profile()
    }
}