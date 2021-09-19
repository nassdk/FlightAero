package com.nassdk.corecommon.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corecommon.extensions.appComponent
import com.nassdk.corecommon.extensions.uiLazy
import com.nassdk.corecommon.navigator.Navigator
import com.nassdk.corenavigation.handler.NavigationHandler
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), NavigationHandler {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by uiLazy {
        Navigator(
            activity = this,
            containerId = containerResId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun openFragment(screen: FragmentScreen) {
        router.navigateTo(screen = screen)
    }

    override fun replaceFragment(screen: FragmentScreen) {
        router.navigateTo(screen = screen)
    }

    override fun newRootFragmentChain(vararg screens: FragmentScreen) {
        router.newRootChain(screens = screens)
    }

    override fun newRootFragment(screen: FragmentScreen) {
        router.newRootScreen(screen = screen)
    }

    override fun returnTo(screen: FragmentScreen) {
        router.backTo(screen = screen)
    }

    override fun exit() {
        router.exit()
    }

    override fun onBackPressed() = router.exit()

    abstract val containerResId: Int
}