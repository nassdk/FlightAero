package com.nassdk.flightaero

import android.os.Bundle
import com.github.terrakok.modo.android.ModoRender
import com.github.terrakok.modo.android.MultiStackFragment
import com.github.terrakok.modo.android.init
import com.github.terrakok.modo.android.saveState
import com.github.terrakok.modo.back
import com.nassdk.corecommon.base.BaseActivity
import com.nassdk.flightaero.mediators.MediatorManager

class AppActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val splashScreen = MediatorManager.splashMediator.getApi().getSplashAppScreen()
            modo.init(bundle = savedInstanceState, firstScreen = splashScreen)
        }
    }

    private val modoRender by lazy {
        object : ModoRender(activity = this@AppActivity, containerId = R.id.appContainer) {
            override fun createMultiStackFragment(): MultiStackFragment {
                return MediatorManager.flowMediator.getApi().getFlowAsMultistackFragment()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        modo.render = modoRender
    }

    override fun onPause() {
        modo.render = null
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        modo.saveState(bundle = outState)
    }

    override fun onBackPressed() = modo.back()
}

