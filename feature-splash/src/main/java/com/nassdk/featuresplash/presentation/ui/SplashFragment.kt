package com.nassdk.featuresplash.presentation.ui

import androidx.lifecycle.lifecycleScope
import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.corecommon.enums.TransitionType
import com.nassdk.featuresplash.R
import com.nassdk.featuresplash.SplashActions
import com.nassdk.featuresplash.SplashFeature
import javax.inject.Inject
import kotlinx.coroutines.delay

internal class SplashFragment : BaseFragment(R.layout.screen_splash) {

    companion object {

        fun newInstance() = SplashFragment()

        private const val SPLASH_DELAY_MILLIS = 1500L
    }

    @Inject lateinit var splashActions: SplashActions

    override val transitionType: TransitionType = TransitionType.NONE

    override fun setupInjection() = SplashFeature.getComponent().inject(this)
    override fun onFinalDestroy() = SplashFeature.destroyModuleGraph()

    override fun setupUi() {

        lifecycleScope.launchWhenStarted {
            delay(SPLASH_DELAY_MILLIS)

            splashActions.openFlowScreen()
        }
    }
}