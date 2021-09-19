package com.nassdk.corenavigation.globalnavigator

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corenavigation.handler.NavigationHandler

interface GlobalNavigator {
    fun setNavigationHandler(navigationHandler: NavigationHandler)
    fun openFragment(screen: FragmentScreen)
    fun replaceFragment(screen: FragmentScreen)
    fun newRootFragment(screen: FragmentScreen)
    fun newRootChain(vararg screens: FragmentScreen)
    fun returnTo(screen: FragmentScreen)
    fun exit()
}