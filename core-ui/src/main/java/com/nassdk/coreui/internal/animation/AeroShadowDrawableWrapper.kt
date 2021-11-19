package com.nassdk.coreui.internal.animation

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.BlurMaskFilter
import android.graphics.BlurMaskFilter.Blur.NORMAL
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.graphics.drawable.DrawableWrapper
import androidx.core.content.withStyledAttributes
import com.nassdk.coreui.R
import com.nassdk.coreui.internal.Constants
import com.nassdk.coreui.internal.Constants.NO_STYLE

@SuppressLint("RestrictedApi")
internal class AeroShadowDrawableWrapper<T : Drawable>(
    drawable: T?,
    context: Context,
    @StyleRes private val shadowStyleRes: Int = NO_STYLE,
) : DrawableWrapper(drawable) {
    private var isNeedCreateShadow = true
    private var shadowBitmap: Bitmap? = null
    private val drawableOffsetXY = IntArray(2)

    @ColorInt
    private var shadowColor: Int = 0
    private var shadowOffsetX: Int = 0
    private var shadowOffsetY: Int = 0
    private var blurRadius: Float = 0F

    private val shadowPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        Paint().apply {
            color = shadowColor
            isFilterBitmap = true
        }
    }

    private val extractPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        Paint().apply {
            maskFilter = BlurMaskFilter(
                blurRadius,
                NORMAL
            )
        }
    }

    init {
        if (shadowStyleRes != Constants.NO_STYLE) {
            context.withStyledAttributes(shadowStyleRes, R.styleable.AeroShadow, ::initShadowStyles)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getDrawable(): T = wrappedDrawable as T

    private fun initShadowStyles(typedArray: TypedArray) {
        with(typedArray) {
            blurRadius = getDimensionPixelOffset(R.styleable.AeroShadow_radius, 0).toFloat()
            shadowColor = getColor(R.styleable.AeroShadow_color, View.NO_ID)
            shadowOffsetX = getDimensionPixelOffset(R.styleable.AeroShadow_offsetX, 0)
            shadowOffsetY = getDimensionPixelOffset(R.styleable.AeroShadow_offsetY, 0)
        }
    }

    private val isNeedDrawShadow: Boolean
        get() = blurRadius > 0 && shadowColor != View.NO_ID

    private fun extractShadow() = getBitmap(
        wrappedDrawable,
        bounds.width(),
        bounds.height()
    ).extractAlpha(extractPaint, drawableOffsetXY)

    private fun Canvas.drawShadow() {
        shadowBitmap?.let { shadow ->
            drawBitmap(
                shadow,
                (bounds.left + drawableOffsetXY[0] + shadowOffsetX).toFloat(),
                (bounds.top + drawableOffsetXY[1] + shadowOffsetY).toFloat(),
                shadowPaint
            )
        }
    }

    private fun getBitmap(drawable: Drawable, width: Int, height: Int): Bitmap =
        Bitmap.createBitmap(width, height, ARGB_8888).apply {
            val oldBounds: Rect = drawable.copyBounds()
            val canvas = Canvas(this)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            drawable.bounds = oldBounds
        }

    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        isNeedCreateShadow = (bounds?.width() ?: 0) > 0 || (bounds?.height() ?: 0) > 0
    }

    override fun draw(canvas: Canvas) {
        if (isNeedDrawShadow) {
            if (isNeedCreateShadow) {
                shadowBitmap = extractShadow()
                isNeedCreateShadow = false
            }
            canvas.drawShadow()
        }
        super.draw(canvas)
    }
}