package com.nassdk.feature_profile.presentation

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.feature_profile.ProfileFeature
import com.nassdk.feature_profile.R
import com.nassdk.feature_profile.databinding.ScreenProfileBinding

internal class ProfileFragment : BaseFragment(R.layout.screen_profile) {

    private val viewBinding by viewBinding(ScreenProfileBinding::bind)

    override fun setupInjection() = ProfileFeature.getComponent().inject(this)
    override fun onFinalDestroy() = ProfileFeature.destroyModuleGraph()

    override fun setupUi() {

        with(viewBinding) {

            toolbar.setupToolbar(
                title = getString(R.string.profile_toolbar_title),
                menuRes = R.menu.menu_profile
            )

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.logOut -> true
                    else -> false
                }
            }
        }
    }

    companion object {

        fun newInstance() = ProfileFragment()
    }
}