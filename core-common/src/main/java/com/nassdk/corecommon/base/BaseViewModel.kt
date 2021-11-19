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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : BaseViewState, E : BaseViewEvent> : ViewModel() {

    private val state by lazy { MutableStateFlow(initialState) }

    protected val viewState
        get() = state.asStateFlow().filterNotNull()

    protected val intent =
        MutableSharedFlow<E>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    protected abstract val initialState: S

    init {
        intent.onEach(::observe).launchIn(viewModelScope)
    }

    protected fun updateState(block: S.() -> S) {

        viewModelScope.launch {
            val currentState = viewState.first()
            state.value = block.invoke(currentState)
        }
    }

    protected abstract fun observe(event: E)

    fun perform(viewEvent: E) = intent.tryEmit(viewEvent)
    fun viewState(): Flow<S> = viewState
}