package com.nassdk.feature_splash.presentation.ui

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        SplashFeature.getComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        if (isRemoving) {
            SplashFeature.destroyModuleGraph()
        }
        super.onDestroy()
    }
}