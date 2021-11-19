package com.nassdk.corecommon.extensions

import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.nassdk.coreui.external.common.AeroClickListener
import com.nassdk.coreui.external.kit.AeroTextView

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

inline fun ViewGroup.executeWithDelayedTransition(block: () -> Unit) {
    TransitionManager.beginDelayedTransition(this, AutoTransition())
    block.invoke()
}

fun AeroTextView.setDrawableTint(
    @ColorRes startDrawableTint: Int? = null,
    @ColorRes topDrawableTint: Int? = null,
    @ColorRes endDrawableTint: Int? = null,
    @ColorRes bottomDrawableTint: Int? = null,
) {

    val drawables = compoundDrawables

    if (startDrawableTint != null) {
        drawables.getOrNull(0)?.run {
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    context,
                    startDrawableTint
                ), BlendModeCompat.SRC_ATOP
            )
        }
    }

    if (topDrawableTint != null) {
        drawables.getOrNull(1)?.run {
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    context,
                    topDrawableTint
                ), BlendModeCompat.SRC_ATOP
            )
        }
    }

    if (endDrawableTint != null) {
        drawables.getOrNull(2)?.run {
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    context,
                    endDrawableTint
                ), BlendModeCompat.SRC_ATOP
            )
        }
    }

    if (bottomDrawableTint != null) {
        drawables.getOrNull(3)?.run {
            colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    context,
                    bottomDrawableTint
                ), BlendModeCompat.SRC_ATOP
            )
        }
    }
}