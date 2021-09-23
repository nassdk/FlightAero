package com.nassdk.corecommon.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nassdk.corecommon.mvi.BaseViewEvent
import com.nassdk.corecommon.mvi.BaseViewState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel<S : BaseViewState, E : BaseViewEvent> : ViewModel() {

    private val state = MutableStateFlow<S?>(null)
    protected val viewState
        get() = state.asStateFlow().filterNotNull()

    protected val intent = MutableSharedFlow<E>(onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        intent.onEach(::observe).launchIn(viewModelScope)
    }

    protected fun updateState(newState: S) {
        state.value = newState
    }

    protected abstract fun observe(event: E)

    fun perform(viewEvent: E) = intent.tryEmit(viewEvent)
    fun viewState(): Flow<S> = viewState
}