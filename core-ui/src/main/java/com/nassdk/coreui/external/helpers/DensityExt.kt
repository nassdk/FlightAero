package com.nassdk.coreui.external.helpers

import android.content.res.Resources
import android.util.DisplayMetrics

private val displayMetrics: DisplayMetrics
    get() = Resources.getSystem().displayMetrics

val Int.toDp: Int get() = (this / displayMetrics.density).toInt()

val Int.toPx: Int get() = (this * displayMetrics.density).toInt()