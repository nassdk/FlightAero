package com.nassdk.corecommon.extensions

import android.view.View
import com.nassdk.coreui.external.common.AeroClickListener

fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.isInvisible(invisible: Boolean) {
    visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

inline fun View.debouncedClick(milliseconds: Long = 500, crossinline listener: (v: View) -> Unit) {
    setOnClickListener {
        object : AeroClickListener(minimumIntervalMillis = milliseconds) {
            override fun onDebouncedClick(v: View) {
                listener.invoke(v)
            }
        }
    }
}