package com.nassdk.featureflow.presentation.ui

import android.view.Menu
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nassdk.FlowFeature
import com.nassdk.TabFragments
import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.corecommon.extensions.uiLazy
import com.nassdk.corecommon.navigator.Navigator
import com.nassdk.featureflow.R
import com.nassdk.featureflow.databinding.ScreenFlowBinding
import com.nassdk.featureflow.domain.entity.FlowTab
import com.nassdk.featureflow.domain.entity.TabFragment
import javax.inject.Inject

internal class FlowFragment : BaseFragment(R.layout.screen_flow) {

    companion object {
        fun newInstance() = FlowFragment()
    }

    override fun setupInjection() = FlowFeature.getComponent().inject(this)
    override fun onFinalDestroy() = FlowFeature.destroyModuleGraph()

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var tabsFragments: TabFragments

    private val viewBinding by viewBinding(ScreenFlowBinding::bind)

    private val navigator by uiLazy {
        Navigator(requireActivity(), R.id.flowContainer)
    }

    private val selectTabListener = BottomNavigationView.OnNavigationItemSelectedListener {
        selectTab(FlowTab.getById(it.itemId))
        true
    }

    private val tabFragments by lazy {
        TabFragment(
            tabsFragments.provideHomeFragment(),
            tabsFragments.provideProfileFragment(),
        )
    }


    override fun setupUi() {
        FlowTab.values().forEach(::addNavigationTab)
    }

    private fun addNavigationTab(tab: FlowTab) {
        val menuItem = viewBinding.navBar.menu
            .add(
                Menu.NONE,
                tab.idRes,
                tab.ordinal,
                getString(tab.titleRes)
            )
        menuItem.setIcon(tab.iconRes)
    }

    private fun selectTab(tab: FlowTab) {
        val currentFragment = childFragmentManager.findFragmentById(R.id.flowContainer)
        val fragment = getFragment(tab)
        childFragmentManager.beginTransaction().apply {
            if (currentFragment != null) {
                detach(currentFragment)
            }
            if (!fragment.isAdded) {
                add(R.id.flowContainer, fragment)
            }
            attach(fragment)
        }.commit()

        viewBinding.navBar.setOnNavigationItemSelectedListener(null)
        viewBinding.navBar.selectedItemId = tab.idRes
        viewBinding.navBar.setOnNavigationItemSelectedListener(selectTabListener)
    }

    private fun getFragment(tab: FlowTab) =
        when (tab) {
            FlowTab.FLIGHTS -> tabFragments.flights
            FlowTab.PROFILE -> tabFragments.profile
        }

    override fun onResume() {
        navigatorHolder.setNavigator(navigator)
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}