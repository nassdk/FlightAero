package com.nassdk.corecommon.extensions

fun Number?.orZero(): Number {

    if (this != null) return this

    return when (this) {
        is Int -> 0
        is Double -> 0.0
        is Float -> 0.0f
        is Long -> 0L
        else -> 0
    }
}

fun Boolean?.orFalse(): Boolean = this ?: false
