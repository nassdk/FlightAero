package com.nassdk.coreui.internal.animation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.BlurMaskFilter
import android.graphics.BlurMaskFilter.Blur.NORMAL
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.appcompat.graphics.drawable.DrawableWrapper
import com.nassdk.coreui.R
import com.nassdk.coreui.internal.Constants.NO_STYLE

@SuppressLint("RestrictedApi")
internal class AeroShadowDrawableWrapper<T : Drawable>(
    drawable: T?,
    private val context: Context,
    @StyleRes private val shadowStyleRes: Int = NO_STYLE
) : DrawableWrapper(drawable) {
    private var isNeedCreateShadow = true
    private var shadowPaint: Paint? = null
    private var shadowBitmap: Bitmap? = null
    private val drawableOffsetXY = IntArray(2)

    @ColorInt
    private var shadowColor: Int = 0
    private var shadowOffsetX: Int = 0
    private var shadowOffsetY: Int = 0
    private var blurRadius: Float = 0F

    init {
        initShadowStyles()
    }

    @Suppress("UNCHECKED_CAST")
    fun getDrawable(): T = wrappedDrawable as T

    private fun initShadowStyles() {
        if (shadowStyleRes != NO_STYLE) {
            with(context.obtainStyledAttributes(shadowStyleRes, R.styleable.AeroShadow)) {
                blurRadius = getDimensionPixelOffset(R.styleable.AeroShadow_radius, 0).toFloat()
                shadowColor = getColor(R.styleable.AeroShadow_color, 0)
                shadowOffsetX = getDimensionPixelOffset(R.styleable.AeroShadow_offsetX, 0)
                shadowOffsetY = getDimensionPixelOffset(R.styleable.AeroShadow_offsetY, 0)

                shadowPaint = Paint().apply {
                    color = shadowColor
                    isFilterBitmap = true
                }

                recycle()
            }
        }
    }

    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        isNeedCreateShadow = true
    }

    override fun draw(canvas: Canvas) {
        if (shadowStyleRes > 0) {
            if (isNeedCreateShadow) {
                createShadow()
                isNeedCreateShadow = false
            }
            drawShadow(canvas)
        }
        super.draw(canvas)
    }

    private fun createShadow() {
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            shadowBitmap = null
            return
        }

        val blurFilter = BlurMaskFilter(
            blurRadius,
            NORMAL
        )

        val shadowPaint = Paint()
        shadowPaint.maskFilter = blurFilter

        val originalBitmap = getBitmap(
            wrappedDrawable,
            bounds.width(),
            bounds.height()
        )

        shadowBitmap = originalBitmap.extractAlpha(shadowPaint, drawableOffsetXY)

        originalBitmap.recycle()
    }

    private fun drawShadow(canvas: Canvas?) {
        shadowBitmap?.let { shadow ->
            canvas?.drawBitmap(
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
}