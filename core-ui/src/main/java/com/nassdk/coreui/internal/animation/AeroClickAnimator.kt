package com.nassdk.coreui.internal.animation

import android.view.MotionEvent
import android.view.View
import com.nassdk.coreui.internal.AeroMotionEventExtensions.onClick
import com.nassdk.coreui.internal.AeroMotionEventExtensions.onPressDown
import com.nassdk.coreui.internal.AeroMotionEventExtensions.onRelease

internal class AeroClickAnimator(
    view: View,
    scaleDownFactor: Float,
    animationDuration: Long
) {
    private val clickAnimator = AeroClickAnimation(view)
    private val releaseAnimator = AeroReleaseAnimation(view)
    private val pressDownAnimator =
        AeroAnimators.createViewScaleAnimator(view, scaleDownFactor, animationDuration)

    fun animateOnTouch(event: MotionEvent?) {
        event?.onPressDown {
            pressDownAnimator.start()
        }?.onClick {
            pressDownAnimator.cancel()
            clickAnimator.start()
        }?.onRelease {
            pressDownAnimator.cancel()
            releaseAnimator.start()
        }
    }

    fun cancel() {
        pressDownAnimator.cancel()
        clickAnimator.cancel()
        releaseAnimator.cancel()
    }
}