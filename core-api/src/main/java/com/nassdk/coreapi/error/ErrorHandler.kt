package com.nassdk.coreapi.error

interface ErrorHandler {
    fun getError(throwable: Throwable)
}