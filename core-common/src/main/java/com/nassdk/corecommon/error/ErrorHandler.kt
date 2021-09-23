package com.nassdk.corecommon.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorWrapper
}