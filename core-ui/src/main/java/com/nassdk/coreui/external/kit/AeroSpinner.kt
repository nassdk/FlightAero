package com.nassdk.coreui.external.kit

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.nassdk.coreui.R

class AeroSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.AeroSpinnerLOrange
) : View(context, attrs, defStyleAttr) {

    private lateinit var spinnerRectF: RectF
    private val spinnerPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var currentArcLengthDecreaseFactor = 0F
    private var currentStartAngle = SPINNER_START_ANGLE
    private var currentSweepAngle = 0F
    private var currentArcLength = SPINNER_ARC_MIN_LENGTH
    private var spinnerSize = Color.BLACK
    private var spinnerStrokeWidth = 0
    private var spinnerStrokeColor = 0

    @Suppress("MagicNumber")
    private val spinAnimation = AnimatorSet().apply {
        playTogether(
            createArcMovingAnimation(
                -45F,
                640F
            ).apply {
                duration = SPIN_DURATION
                interpolator = FastOutSlowInInterpolator()
            },
            createArcChangeLengthAnimation(SPINNER_ARC_MAX_LENGTH).apply {
                duration = SPIN_DURATION * 1 / 3
            },
            createArcChangeLengthAnimation(SPINNER_ARC_MIN_LENGTH).apply {
                duration = SPIN_DURATION * 2 / 3
                startDelay = SPIN_DURATION * 1 / 3
            }
        )
    }

    @Suppress("MagicNumber")
    private val spinEndAnimation = createArcMovingAnimation(
        640F - MAX_ANGLE - MAX_ANGLE,
        -230F
    ).apply {
        duration = SPIN_END_DURATION
        interpolator = FastOutSlowInInterpolator()
    }

    private val infinityLooperListener = object : AnimatorListenerAdapter() {
        private var isAnimationCanceled = false
        override fun onAnimationStart(animation: Animator?) {
            isAnimationCanceled = false
        }

        override fun onAnimationCancel(animation: Animator?) {
            isAnimationCanceled = true
        }

        override fun onAnimationEnd(animation: Animator?) {
            if (!isAnimationCanceled) {
                currentArcLength = SPINNER_ARC_MIN_LENGTH
                currentArcLengthDecreaseFactor = 0F
                runSpinnerLoopSetAnimationIfNeed()
            }
        }
    }

    private val spinnerLoopSet = AnimatorSet().apply {
        playSequentially(spinAnimation, spinEndAnimation)
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.AeroSpinner, defStyleAttr, defStyleRes) {
            spinnerSize = getDimensionPixelSize(R.styleable.AeroSpinner_spinnerSize, 0)
            spinnerStrokeWidth =
                getDimensionPixelSize(R.styleable.AeroSpinner_spinnerStrokeWidth, 0)
            spinnerStrokeColor = getColor(R.styleable.AeroSpinner_spinnerStrokeColor, 0)

            initialize()
        }
    }

    fun setStyle(@StyleRes styleResId: Int) {
        context.withStyledAttributes(
            styleResId,
            R.styleable.AeroSpinner,
            block = {
                initialize()
            }
        )
    }

    private fun initialize() {
        setupSpinnerPaintAndRect()
        runSpinnerLoopSetAnimationIfNeed()
    }

    private fun runSpinnerLoopSetAnimationIfNeed() {
        if (!spinnerLoopSet.isRunning && visibility == VISIBLE) {
            spinnerLoopSet.start()
        }
    }

    private fun setupSpinnerPaintAndRect() {
        spinnerPaint.apply {
            color = spinnerStrokeColor
            strokeWidth = spinnerStrokeWidth.toFloat()
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }

        val offset = getOffsetForArcFitInSquare()
        val diameter = spinnerSize - offset

        spinnerRectF = RectF(
            offset,
            offset,
            diameter,
            diameter
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val spinnerSize =
            MeasureSpec.makeMeasureSpec(
                spinnerSize,
                MeasureSpec.EXACTLY
            )

        super.onMeasure(
            spinnerSize,
            spinnerSize
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawSpinnerArc(canvas)
    }

    private fun getOffsetForArcFitInSquare() = spinnerPaint.strokeWidth / 2

    private fun drawSpinnerArc(canvas: Canvas) {
        canvas.drawArc(
            spinnerRectF,
            currentStartAngle,
            currentSweepAngle,
            false,
            spinnerPaint
        )
    }

    private fun createArcMovingAnimation(
        startAngle: Float,
        sweepAngle: Float
    ) =
        ValueAnimator.ofFloat(startAngle, sweepAngle).apply {
            addUpdateListener { valueAnimator ->
                val currentAngle = valueAnimator.animatedValue as Float
                currentStartAngle = currentAngle + currentArcLengthDecreaseFactor
                currentSweepAngle = currentArcLength - currentArcLengthDecreaseFactor
                invalidate()
            }
        }

    private fun createArcChangeLengthAnimation(
        @FloatRange(
            from = SPINNER_ARC_MIN_LENGTH.toDouble(),
            to = SPINNER_ARC_MAX_LENGTH.toDouble()
        )
        newLength: Float
    ) =
        ValueAnimator.ofFloat(currentArcLength, newLength).apply {
            addUpdateListener { valueAnimator ->
                val arcLength = valueAnimator.animatedValue as Float
                if (currentArcLength < newLength) {
                    currentArcLengthDecreaseFactor = 0F
                    currentArcLength = arcLength
                } else {
                    currentArcLengthDecreaseFactor =
                        (currentArcLength - newLength) * animatedFraction
                }
            }
        }

    override fun onAttachedToWindow() {
        spinnerLoopSet.addListener(infinityLooperListener)
        runSpinnerLoopSetAnimationIfNeed()
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        spinnerLoopSet.cancel()
        spinnerLoopSet.removeAllListeners()
        super.onDetachedFromWindow()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == VISIBLE) {
            runSpinnerLoopSetAnimationIfNeed()
        } else {
            spinnerLoopSet.cancel()
        }
    }

    companion object {
        private const val SPIN_DURATION = 1000L
        private const val SPIN_END_DURATION = 700L
        private const val MAX_ANGLE = 360
        private const val SPINNER_START_ANGLE = -45F
        private const val SPINNER_ARC_MIN_LENGTH = 135F
        private const val SPINNER_ARC_MAX_LENGTH = 320F
    }
}
