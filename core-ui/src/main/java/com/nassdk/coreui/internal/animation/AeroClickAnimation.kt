package com.nassdk.coreui.internal.animation

import android.view.View
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

internal class AeroClickAnimation(
    view: View,
    private val startVelocity: Float = DEFAULT_CLICK_START_VELOCITY
) {
    private val clickScaleX = SpringAnimation(view, SpringAnimation.SCALE_X)
    private val clickScaleY = SpringAnimation(view, SpringAnimation.SCALE_Y)

    private val clickAnimationSpringForce = SpringForce().apply {
        finalPosition = 1f
        stiffness = STIFFNESS
        dampingRatio = DAMPING_RATIO
    }

    fun start() {
        clickScaleX.addClickSpringForce().start()
        clickScaleY.addClickSpringForce().start()
    }

    fun cancel() {
        clickScaleX.cancel()
        clickScaleY.cancel()
    }

    private fun SpringAnimation.addClickSpringForce() =
        apply {
            setStartVelocity(startVelocity)
            spring = clickAnimationSpringForce
        }

    companion object {
        private const val DEFAULT_CLICK_START_VELOCITY = -2F
        private const val STIFFNESS = 500F
        private const val DAMPING_RATIO = .3F
    }
}