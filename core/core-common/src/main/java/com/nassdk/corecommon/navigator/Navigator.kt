package com.nassdk.corecommon.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corecommon.R
import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.corecommon.enums.TransitionType
import com.nassdk.corecommon.extensions.hideKeyboard

class Navigator(
    activity: FragmentActivity,
    containerId: Int
) : AppNavigator(
    activity = activity,
    containerId = containerId
) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {

        val transitionType = (nextFragment as BaseFragment).transitionType

        if (currentFragment == null
            || currentFragment == nextFragment
            || transitionType == TransitionType.NONE
        ) return

        if (transitionType == TransitionType.HORIZONTAL) {
            fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
        }

        if (transitionType == TransitionType.VERTICAL) {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_bottom,
                R.anim.slide_out_top,
                R.anim.slide_in_top,
                R.anim.slide_out_bottom
            )
        }
    }

    override fun applyCommands(commands: Array<out Command>) {
        activity.hideKeyboard()
        applyCommandsSync(commands)
    }
}