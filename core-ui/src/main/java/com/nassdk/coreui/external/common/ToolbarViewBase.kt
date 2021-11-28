package com.nassdk.coreui.external.common

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.nassdk.coreui.R
import com.nassdk.coreui.databinding.ToolbarBinding

class ToolbarViewBase @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : Toolbar(context, attrs, defStyleAttr) {

    private val viewBinding: ToolbarBinding

    init {

        setBackgroundColor(ContextCompat.getColor(context, R.color.ui_white))

        val view = inflate(context, R.layout.toolbar, this)

        viewBinding = ToolbarBinding.bind(view).apply {
            TypedValue().run {
                if (context.theme.resolveAttribute(android.R.attr.actionBarSize, this, true)) {
                    root.minimumHeight =
                        TypedValue.complexToDimensionPixelSize(data, resources.displayMetrics)
                }
            }
        }
    }

    fun setupToolbar(
        title: String? = null,
        backButtonListener: (() -> Unit)? = null,
        @MenuRes menuRes: Int? = null,
        @DrawableRes customNavIcon: Int? = null,
    ) {
        with(viewBinding) {

            backButtonListener?.let {
                toolbar.navigationIcon =
                    ContextCompat.getDrawable(context, customNavIcon ?: R.drawable.icv_button_close)
                setNavigationOnClickListener { backButtonListener.invoke() }
            }

            menuRes?.let {
                if (menu.size() <= 0) inflateMenu(it)
            }

            titleText.text = title.orEmpty()

            navIconPlaceholder.isVisible = backButtonListener == null
            menuIconPlaceholder.isVisible = menuRes == null
        }
    }
}