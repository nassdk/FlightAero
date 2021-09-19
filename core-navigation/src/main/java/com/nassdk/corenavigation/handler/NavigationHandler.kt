package com.nassdk.corenavigation.handler

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface NavigationHandler {

    fun openFragment(screen: FragmentScreen)
    fun replaceFragment(screen: FragmentScreen)
    fun newRootFragment(screen: FragmentScreen)
    fun newRootFragmentChain(vararg screens: FragmentScreen)
    fun returnTo(screen: FragmentScreen)
    fun exit()
}