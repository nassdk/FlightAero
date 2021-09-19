package com.nassdk.corecommon.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nassdk.corecommon.enums.TransitionType

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {
    open val transitionType: TransitionType = TransitionType.HORIZONTAL
}