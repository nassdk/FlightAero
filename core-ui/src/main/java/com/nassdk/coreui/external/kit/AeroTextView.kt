package com.nassdk.coreui.external.kit

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.withStyledAttributes
import com.nassdk.coreui.R

open class AeroTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val androidTextColorAttr = intArrayOf(android.R.attr.textColor)
    private val defaultTextColor = ContextCompat.getColor(context, R.color.ui_primary_white)
    private var baselineToTop = 0
    private var baselineToBottom = 0

    init {
        letterSpacing = LETTER_SPACING
        includeFontPadding = false

        context.withStyledAttributes(attrs, androidTextColorAttr, defStyleAttr, defStyleRes) {
            val textColor = try {
                getColorOrThrow(0)
            } catch (e: IllegalArgumentException) {
                null
            }
            this@AeroTextView.setTextColor(textColor ?: defaultTextColor)
        }
    }

    private companion object {
        private const val LETTER_SPACING = -0.01F
    }
}