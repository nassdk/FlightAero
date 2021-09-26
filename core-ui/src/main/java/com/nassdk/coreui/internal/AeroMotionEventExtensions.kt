package com.nassdk.coreui.internal

import android.view.MotionEvent

internal object AeroMotionEventExtensions {
    private const val CLICK_TIME_THRESHOLD = 100L
    inline fun MotionEvent.onPressDown(callback: () -> Unit): MotionEvent {
        when (actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                callback.invoke()
            }
        }
        return this
    }

    inline fun MotionEvent.onClick(callback: () -> Unit): MotionEvent {
        when (actionMasked) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (eventTime - downTime < CLICK_TIME_THRESHOLD) {
                    callback.invoke()
                }
            }
        }
        return this
    }

    inline fun MotionEvent.onRelease(callback: () -> Unit): MotionEvent {
        when (actionMasked) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (eventTime - downTime > CLICK_TIME_THRESHOLD) {
                    callback.invoke()
                }
            }
        }
        return this
    }
}