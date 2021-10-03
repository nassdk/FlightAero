package com.nassdk.corecommon.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nassdk.corecommon.enums.TransitionType
import com.nassdk.corecommon.extensions.orFalse

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    open val transitionType: TransitionType = TransitionType.HORIZONTAL

    protected abstract fun setupInjection()
    protected open fun setupUi() = Unit

    override fun onAttach(context: Context) {
        setupInjection()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRealDestroy()) {
            onFinalDestroy()
        }
    }

    open fun onFinalDestroy() = Unit

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