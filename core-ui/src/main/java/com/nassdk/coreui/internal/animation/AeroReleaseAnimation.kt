package com.nassdk.coreui.internal.animation

import android.view.View
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

internal class AeroReleaseAnimation(view: View) {
    private val releaseScaleX = SpringAnimation(view, SpringAnimation.SCALE_X)
    private val releaseScaleY = SpringAnimation(view, SpringAnimation.SCALE_Y)

    private val releaseAnimationSpringForce = SpringForce().apply {
        finalPosition = 1f
        stiffness = SpringForce.STIFFNESS_LOW
        dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    }

    fun start() {
        releaseScaleX.addReleaseSpringForce().start()
        releaseScaleY.addReleaseSpringForce().start()
    }

    fun cancel() {
        releaseScaleX.cancel()
        releaseScaleY.cancel()
    }

    private fun SpringAnimation.addReleaseSpringForce() =
        apply {
            spring = releaseAnimationSpringForce
        }
}