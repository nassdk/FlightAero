package com.nassdk.corenavigation.globalnavigator

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corenavigation.handler.NavigationHandler

class GlobalNavigatorImpl : GlobalNavigator {

    private var navigationHandler: NavigationHandler? = null

    override fun setNavigationHandler(navigationHandler: NavigationHandler) {
        this.navigationHandler = navigationHandler
    }

    override fun openFragment(screen: FragmentScreen) {
        navigationHandler?.openFragment(screen = screen)
    }

    override fun replaceFragment(screen: FragmentScreen) {
        navigationHandler?.replaceFragment(screen = screen)
    }

    override fun newRootFragment(screen: FragmentScreen) {
        navigationHandler?.newRootFragment(screen = screen)
    }

    override fun newRootChain(vararg screens: FragmentScreen) {
        navigationHandler?.newRootFragmentChain(screens = screens)
    }

    override fun returnTo(screen: FragmentScreen) {
        navigationHandler?.returnTo(screen = screen)
    }



    override fun exit() {
        navigationHandler?.exit()
    }

}