package com.nassdk.corecommon.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nassdk.corecommon.extensions.appComponent
import com.nassdk.corecommon.extensions.uiLazy
import com.nassdk.corecommon.navigator.Navigator
import com.nassdk.corecommon.patterns.BottomDialogFragment
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.corenavigation.handler.NavigationHandler
import com.nassdk.coreui.external.common.BottomDialogParams
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), NavigationHandler {

    @Inject lateinit var router: Router
    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var globalNavigator: GlobalNavigator

    private val navigator by uiLazy {
        Navigator(
            activity = this,
            containerId = containerResId
        )
    }

    protected var currentBottomFragment: BottomDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        globalNavigator.setNavigationHandler(this)
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

    override fun openBottomFragment(
        fragment: Fragment,
        bottomDialogParams: BottomDialogParams
    ) {
        currentBottomFragment?.dismiss()
        currentBottomFragment = null

        currentBottomFragment = BottomDialogFragment.newInstance(bottomDialogParams)
        currentBottomFragment?.setOnDismissListener { currentBottomFragment = null }

        currentBottomFragment?.createFragmentFunction = { fragment }

        currentBottomFragment?.show(supportFragmentManager, BOTTOM_FRAGMENT_TAG)
    }

    override fun closeBottomFragment() {
        if (currentBottomFragment != null) {
            currentBottomFragment?.dismiss()
        } else {
            (supportFragmentManager.findFragmentByTag(
                BOTTOM_FRAGMENT_TAG
            ) as? BottomDialogFragment)?.dismiss()
        }
        currentBottomFragment = null
    }

    override fun exit() {
        router.exit()
    }

    override fun onBackPressed() = router.exit()

    abstract val containerResId: Int

    private companion object {
        private val BOTTOM_FRAGMENT_TAG = BottomDialogFragment::class.java.simpleName
    }
}