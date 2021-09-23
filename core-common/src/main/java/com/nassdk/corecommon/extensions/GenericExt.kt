package com.nassdk.corecommon.extensions

fun <T> T?.or(alternative: T): T = this ?: alternative