package com.nassdk.feature_splash.presentation.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.feature_splash.R
import com.nassdk.feature_splash.SplashFeature

class SplashFragment : BaseFragment(R.layout.screen_splash) {

    companion object {

        fun newInstance() = FragmentScreen {
            SplashFragment()
        }
    }

    override fun setupInjection() = SplashFeature.getComponent().inject(this)
    override fun onFinalDestroy() = SplashFeature.destroyModuleGraph()
}