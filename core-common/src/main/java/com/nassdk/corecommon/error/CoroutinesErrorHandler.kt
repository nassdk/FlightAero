package com.nassdk.corecommon.error

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

internal class CoroutinesErrorHandler(
    private val exceptionHandler: ((ErrorWrapper) -> Unit)? = null,
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val mappedException = CoreErrorHandler.mapException(throwable = exception)
        exceptionHandler?.invoke(mappedException)
    }
}