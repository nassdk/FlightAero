package com.nassdk.coreui.external.common

import android.os.SystemClock
import android.view.View
import java.util.*
import kotlin.math.abs


/**
 * A Debounced OnClickListener
 * Rejects clicks that are too close together in time.
 * This class is safe to use as an OnClickListener for multiple views, and will debounce each one separately.
 */
abstract class AeroClickListener(
    private val minimumIntervalMillis: Long
) : View.OnClickListener {

    private val lastClickMap: MutableMap<View, Long>

    /**
     * Implement this in your subclass instead of onClick
     * @param v The view that was clicked
     */
    abstract fun onDebouncedClick(v: View)

    override fun onClick(clickedView: View) {

        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp

        if (previousClickTimestamp == null
            || abs(currentTimestamp - previousClickTimestamp) > minimumIntervalMillis
        ) {
            onDebouncedClick(clickedView)
        }
    }

    /**
     * The one and only constructor
     * @param minimumIntervalMillis The minimum allowed time between clicks - any click sooner than this after a previous click will be rejected
     */
    init {
        lastClickMap = WeakHashMap()
    }
}