package com.nassdk.corecommon.error

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

internal class CoroutinesErrorHandler(
    private val errorHandler: (error: ErrorWrapper) -> Unit,
) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        errorHandler.invoke(ErrorWrapper.ServerError)
    }
}