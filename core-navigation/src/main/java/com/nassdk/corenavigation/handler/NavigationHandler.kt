package com.nassdk.corenavigation.handler

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.coreui.external.common.BottomDialogParams

interface NavigationHandler {

    fun openFragment(screen: FragmentScreen)
    fun replaceFragment(screen: FragmentScreen)
    fun newRootFragment(screen: FragmentScreen)
    fun newRootFragmentChain(vararg screens: FragmentScreen)
    fun openBottomFragment(fragment: Fragment, bottomDialogParams: BottomDialogParams)
    fun closeBottomFragment()
    fun returnTo(screen: FragmentScreen)
    fun exit()
}