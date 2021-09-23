package com.nassdk.corecommon.extensions

fun <T> T?.ifNull(alternative: T): T = this ?: alternative