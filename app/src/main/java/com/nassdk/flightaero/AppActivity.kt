package com.nassdk.flightaero

import android.os.Bundle
import com.nassdk.corecommon.base.BaseActivity
import com.nassdk.feature_splash.presentation.ui.SplashFragment

class AppActivity : BaseActivity() {

    override val containerResId: Int
        get() = R.id.appContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            globalNavigator.newRootFragment(screen = SplashFragment.newInstance())
        }
    }
}