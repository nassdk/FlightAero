package com.nassdk.coreui.internal.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.StateListAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator
import androidx.annotation.ColorInt
import androidx.dynamicanimation.animation.DynamicAnimation
import com.nassdk.coreui.internal.AeroGradientDrawable

internal object AeroAnimators {

    private const val ANIMATION_DURATION = 300L

    fun DynamicAnimation<out DynamicAnimation<*>>.doOnceOnEnd(doOnEndCallback: () -> Unit) {
        addEndListener(
            object : DynamicAnimation.OnAnimationEndListener {
                override fun onAnimationEnd(
                    animation: DynamicAnimation<out DynamicAnimation<*>>,
                    canceled: Boolean,
                    value: Float,
                    velocity: Float
                ) {
                    doOnEndCallback.invoke()
                    animation.removeEndListener(this)
                }
            }
        )
    }

    fun createViewScaleAnimator(
        view: View,
        scaleFactor: Float,
        duration: Long? = null,
        interpolator: Interpolator? = null
    ) = ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.SCALE_X, scaleFactor),
        PropertyValuesHolder.ofFloat(View.SCALE_Y, scaleFactor)
    ).apply {
        duration?.run(::setDuration)
        interpolator?.run(::setInterpolator)
    }

    /***
     * Doesn't work with the alpha channel well
     */
    fun createColorTransitionAnimator(
        viewWithViennaGradientColorDrawable: View,
        @ColorInt endColorInt: Int,
        @ColorInt endBorderColorInt: Int = -1,
        duration: Long
    ): AnimatorSet =
        AnimatorSet().apply {
            val bgDrawable =
                viewWithViennaGradientColorDrawable.background as AeroGradientDrawable
            val bgColor = bgDrawable.getSolidColor()
            val strokeColor = bgDrawable.getStrokeColor()
            setDuration(duration)
            val backgroundAnimator = ValueAnimator.ofArgb(
                bgColor,
                endColorInt
            ).apply {
                addUpdateListener { animator ->
                    val newColor = animator.animatedValue as Int
                    with(viewWithViennaGradientColorDrawable.background.mutate() as AeroGradientDrawable) {
                        setColor(newColor)
                    }
                }
            }
            val strokeAnimator =
                ValueAnimator.ofArgb(
                    strokeColor,
                    endBorderColorInt
                ).apply {
                    addUpdateListener { animator ->
                        val newColor = animator.animatedValue as Int
                        with(viewWithViennaGradientColorDrawable.background.mutate() as AeroGradientDrawable) {
                            setStrokeColor(newColor)
                        }
                    }
                }
            if (endBorderColorInt > 0) {
                playTogether(
                    backgroundAnimator,
                    strokeAnimator
                )
            } else {
                play(backgroundAnimator)
            }
        }

    private fun createBgColorChangeAnimator(
        viewWithViennaGradientColorDrawable: View,
        @ColorInt startColorInt: Int,
        @ColorInt endColorInt: Int,
        duration: Long
    ): ValueAnimator =
        ValueAnimator.ofArgb(
            startColorInt,
            endColorInt
        ).apply {
            setDuration(duration)
            addUpdateListener { animator ->
                val newColor = animator.animatedValue as Int
                with(viewWithViennaGradientColorDrawable.background.mutate() as AeroGradientDrawable) {
                    setColor(newColor)
                }
            }
        }

    private fun createStrokeColorChangeAnimator(
        viewWithViennaGradientColorDrawable: View,
        @ColorInt startColorInt: Int,
        @ColorInt endColorInt: Int,
        duration: Long
    ): ValueAnimator =
        ValueAnimator.ofArgb(
            startColorInt,
            endColorInt
        ).apply {
            setDuration(duration)
            addUpdateListener { animator ->
                val newColor = animator.animatedValue as Int
                with(viewWithViennaGradientColorDrawable.background.mutate() as AeroGradientDrawable) {
                    setStrokeColor(newColor)
                }
            }
        }

    @Suppress("LongParameterList")
    fun createBgColorChangeAnimator(
        viewWithViennaGradientColorDrawable: View,
        duration: Long,
        @ColorInt startColorInt: Int,
        @ColorInt endColorInt: Int,
        @ColorInt startStrokeColorInt: Int? = null,
        @ColorInt endStrokeColorInt: Int? = null
    ): AnimatorSet =
        AnimatorSet().apply {
            val animatorsList =
                if (startStrokeColorInt != null && endStrokeColorInt != null) {
                    listOf(
                        createBgColorChangeAnimator(
                            viewWithViennaGradientColorDrawable,
                            startColorInt,
                            endColorInt,
                            duration
                        ),
                        createStrokeColorChangeAnimator(
                            viewWithViennaGradientColorDrawable,
                            startStrokeColorInt,
                            endStrokeColorInt,
                            duration
                        )
                    )
                } else {
                    listOf(
                        createBgColorChangeAnimator(
                            viewWithViennaGradientColorDrawable,
                            startColorInt,
                            endColorInt,
                            duration
                        )
                    )
                }
            playTogether(animatorsList)
        }

    fun createCellPressedAnimator(
        viewWithViennaGradientColorDrawable: View,
        @ColorInt startColorInt: Int,
        @ColorInt endColorInt: Int
    ) = StateListAnimator().apply {
        addState(
            intArrayOf(-android.R.attr.state_enabled),
            createBgColorChangeAnimator(
                viewWithViennaGradientColorDrawable,
                ANIMATION_DURATION,
                startColorInt,
                startColorInt
            )
        )
        addState(
            intArrayOf(-android.R.attr.state_pressed, android.R.attr.state_enabled),
            createBgColorChangeAnimator(
                viewWithViennaGradientColorDrawable,
                ANIMATION_DURATION,
                endColorInt,
                startColorInt
            )
        )
        addState(
            intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled),
            createBgColorChangeAnimator(
                viewWithViennaGradientColorDrawable,
                ANIMATION_DURATION,
                startColorInt,
                endColorInt
            )
        )
    }
}