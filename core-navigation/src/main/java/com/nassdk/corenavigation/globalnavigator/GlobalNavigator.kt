package com.nassdk.corenavigation.globalnavigator

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corenavigation.handler.NavigationHandler
import com.nassdk.coreui.external.common.BottomDialogParams

interface GlobalNavigator {
    fun setNavigationHandler(navigationHandler: NavigationHandler)
    fun openFragment(screen: FragmentScreen)
    fun replaceFragment(screen: FragmentScreen)
    fun newRootFragment(screen: FragmentScreen)
    fun newRootChain(vararg screens: FragmentScreen)
    fun openBottomFragment(fragment: Fragment, bottomDialogParams: BottomDialogParams)
    fun closeBottomFragment()
    fun returnTo(screen: FragmentScreen)
    fun exit()
}