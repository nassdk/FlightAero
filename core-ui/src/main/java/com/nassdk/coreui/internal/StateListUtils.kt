package com.nassdk.coreui.internal

import android.content.res.ColorStateList
import androidx.annotation.ColorInt

internal object StateListUtils {

    fun createColorStateList(
        @ColorInt defaultColorInt: Int,
        @ColorInt disabledColorInt: Int = defaultColorInt,
        @ColorInt pressedColorInt: Int = defaultColorInt
    ) = ColorStateList(
        DEFAULT_STATE_LIST,
        intArrayOf(
            defaultColorInt,
            disabledColorInt,
            if (pressedColorInt != 0) pressedColorInt else defaultColorInt
        )
    )

    fun createCheckedColorStateList(
        @ColorInt checkedEnabledColor: Int,
        @ColorInt checkedDisabledColor: Int = checkedEnabledColor,
        @ColorInt unCheckedEnabledColor: Int,
        @ColorInt unCheckedDisabledColor: Int = unCheckedEnabledColor
    ) = ColorStateList(
        CHECKED_STATE_LIST,
        intArrayOf(
            unCheckedDisabledColor,
            checkedDisabledColor,
            unCheckedEnabledColor,
            checkedEnabledColor
        )
    )

    private val DEFAULT_STATE_LIST =
        arrayOf(
            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_pressed),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed)
        )

    private val CHECKED_STATE_LIST =
        arrayOf(
            intArrayOf(-android.R.attr.state_checked, -android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_checked, -android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_checked, android.R.attr.state_enabled),
            intArrayOf()
        )
}