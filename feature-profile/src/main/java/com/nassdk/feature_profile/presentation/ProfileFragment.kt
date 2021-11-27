package com.nassdk.feature_profile.presentation

import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.feature_profile.ProfileFeature
import com.nassdk.feature_profile.R

internal class ProfileFragment : BaseFragment(R.layout.screen_profile) {

    override fun setupInjection() = ProfileFeature.getComponent().inject(this)
    override fun onFinalDestroy() = ProfileFeature.destroyModuleGraph()

    companion object {

        fun newInstance() = ProfileFragment()
    }
}