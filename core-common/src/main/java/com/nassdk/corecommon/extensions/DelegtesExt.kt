package com.nassdk.corecommon.extensions

fun <T> uiLazy(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}
