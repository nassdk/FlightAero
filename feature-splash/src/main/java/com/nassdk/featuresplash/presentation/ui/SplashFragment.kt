package com.nassdk.featuresplash.presentation.ui

import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.featuresplash.R
import com.nassdk.featuresplash.SplashFeature

internal class SplashFragment : BaseFragment(R.layout.screen_splash) {

    companion object {

        fun newInstance() = SplashFragment()
    }

    override fun setupInjection() = SplashFeature.getComponent().inject(this)
    override fun onFinalDestroy() = SplashFeature.destroyModuleGraph()
}