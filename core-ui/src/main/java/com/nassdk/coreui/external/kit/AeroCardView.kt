package com.nassdk.coreui.external.kit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.nassdk.coreui.R
import com.nassdk.coreui.internal.Constants.NO_STYLE
import com.nassdk.coreui.internal.animation.AeroClickAnimator
import com.nassdk.coreui.internal.animation.AeroShadowDrawableWrapper

open class AeroCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Dimension
    private var borderRadius = resources.getDimension(R.dimen.border_radius_L)

    @StyleRes
    private var shadowStyleResId = NO_STYLE

    @ColorInt
    private var cardBackgroundColor =
        ContextCompat.getColor(context, R.color.ui_primary_white)

    private val cardClickAnimator = AeroClickAnimator(this, SCALE_DOWN_FACTOR, ANIMATION_DURATION)

    private var hasClickAnimation: Boolean = false

    private var childrenClipPath: Path = Path()

    init {
        clipChildren = false
        clipToPadding = false
        isClickable = true

        context.withStyledAttributes(attrs, R.styleable.AeroCardView) {
            cardBackgroundColor = getColor(
                R.styleable.AeroCardView_cardBackgroundColor,
                ContextCompat.getColor(context, R.color.ui_primary_white)
            )

            shadowStyleResId = getResourceId(
                R.styleable.AeroCardView_cardShadowStyle,
                NO_STYLE
            )

            borderRadius = getDimensionPixelSize(
                R.styleable.AeroCardView_cardBorderRadius,
                0
            ).toFloat()

            hasClickAnimation = getBoolean(
                R.styleable.AeroCardView_cardHasClickAnimation,
                false
            )

            setupBackground()
        }
    }

    fun setCanAnimateOnClick(hasClickAnimation: Boolean) {
        this.hasClickAnimation = hasClickAnimation
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (hasClickAnimation) {
            cardClickAnimator.animateOnTouch(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onDetachedFromWindow() {
        cardClickAnimator.cancel()
        super.onDetachedFromWindow()
    }

    fun setCardBackgroundColor(@ColorRes colorRes: Int) {
        cardBackgroundColor = ContextCompat.getColor(context, colorRes)
        setupBackground()
    }

    fun setBorderRadii(@DimenRes borderRadiiDimenRes: Int) {
        borderRadius = resources.getDimensionPixelSize(borderRadiiDimenRes).toFloat()
        setupBackground()
    }

    fun setShadowStyle(@StyleRes shadowStyleResId: Int) {
        this.shadowStyleResId = shadowStyleResId
        setupBackground()
    }

    override fun drawChild(canvas: Canvas, child: View?, drawingTime: Long): Boolean {
        canvas.save()
        canvas.clipPath(childrenClipPath)
        val result: Boolean = super.drawChild(canvas, child, drawingTime)
        canvas.restore()
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        childrenClipPath = Path()
        childrenClipPath.addRoundRect(
            RectF(0F, 0F, w.toFloat(), h.toFloat()),
            borderRadius,
            borderRadius,
            Path.Direction.CW
        )
    }

    private fun setupBackground() {
        background = AeroShadowDrawableWrapper(
            GradientDrawable().apply {
                setColor(cardBackgroundColor)
                cornerRadius = borderRadius
            },
            context,
            shadowStyleResId
        )
        invalidate()
    }

    companion object {
        private const val SCALE_DOWN_FACTOR = .97F
        private const val ANIMATION_DURATION = 300L
    }
}