package com.nassdk.corecommon.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nassdk.corecommon.enums.TransitionType
import com.nassdk.corecommon.extensions.orFalse

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    open val transitionType: TransitionType = TransitionType.HORIZONTAL

    protected abstract fun setupInjection()

    override fun onAttach(context: Context) {
        setupInjection()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRealDestroy()) {
            onFinalDestroy()
        }
    }

    protected open fun onFinalDestroy() = Unit

    private fun isRealDestroy(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    private fun isRealRemoving(): Boolean {
        return (isRemoving) ||
                ((parentFragment as? BaseFragment)?.isRealRemoving().orFalse())
    }
}