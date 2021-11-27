package com.nassdk.feature_profile.presentation.navigation

import com.github.terrakok.modo.android.AppScreen
import com.nassdk.feature_profile.presentation.ProfileFragment
import kotlinx.parcelize.Parcelize

internal object Screens {

    @Parcelize
    class Profile : AppScreen("Profile") {
        override fun create() = ProfileFragment.newInstance()
    }
}