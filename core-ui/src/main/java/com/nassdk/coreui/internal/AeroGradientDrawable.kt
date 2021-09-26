package com.nassdk.coreui.internal

import android.graphics.drawable.GradientDrawable

internal class AeroGradientDrawable : GradientDrawable() {
    private var currentGradientDrawableSolidColor: Int = 0
    private var currentGradientDrawableStrokeColor: Int = 0
    private var currentGradientDrawableStrokeWidth: Int = 0
    override fun setColor(argb: Int) {
        super.setColor(argb)
        currentGradientDrawableSolidColor = argb
    }
    override fun setStroke(width: Int, color: Int) {
        super.setStroke(width, color)
        currentGradientDrawableStrokeColor = color
        currentGradientDrawableStrokeWidth = width
    }
    fun setStrokeColor(color: Int) {
        super.setStroke(currentGradientDrawableStrokeWidth, color)
        currentGradientDrawableStrokeColor = color
    }
    fun getSolidColor() = currentGradientDrawableSolidColor
    fun getStrokeColor() = currentGradientDrawableStrokeColor
}