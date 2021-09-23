package com.nassdk.corecommon.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nassdk.corecommon.mvi.BaseViewEvent
import com.nassdk.corecommon.mvi.BaseViewState
import kotlinx.coroutines.flow.collect

abstract class BaseMviFragment<S : BaseViewState, E : BaseViewEvent, VM : BaseViewModel<S, E>>(
    @LayoutRes layoutResId: Int
) : BaseFragment(layoutResId = layoutResId) {

    protected abstract val viewModel: VM
    protected abstract fun render(state: S)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState().collect(::render)
            }
        }
    }
}