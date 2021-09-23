package com.nassdk.corecommon.extensions

import android.view.View

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
        object : com.nassdk.coreui.DebouncedOnClickListener(minimumIntervalMillis = milliseconds) {
            override fun onDebouncedClick(v: View) {
                listener.invoke(v)
            }
        }
    }
}