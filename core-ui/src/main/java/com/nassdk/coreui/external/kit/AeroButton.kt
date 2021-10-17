package com.nassdk.coreui.external.kit

import android.animation.StateListAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isGone
import com.nassdk.coreui.R
import com.nassdk.coreui.external.helpers.toPx
import com.nassdk.coreui.internal.AeroGradientDrawable
import com.nassdk.coreui.internal.Constants.NO_STYLE
import com.nassdk.coreui.internal.Constants.TRANSPARENT_COLOR
import com.nassdk.coreui.internal.StateListUtils
import com.nassdk.coreui.internal.animation.AeroClickAnimator
import com.nassdk.coreui.internal.animation.AeroShadowDrawableWrapper

class AeroButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0/*,
    defStyleRes: Int = R.style.ViennaButtonAccent*/
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var titleTextView: AeroTextView
    private lateinit var spinner: AeroSpinner
    private lateinit var buttonBackgroundDrawable: GradientDrawable

    private var buttonBorderRadius: Int = 0

    private var buttonBorderWidth: Int = 0

    private var buttonTitle: String? = null

    private var buttonSpinnerSize: Int = 0

    private var buttonDisabledSpinnerStyleId: Int = R.style.AeroSpinnerLGray

    private var buttonSpinnerStyleId: Int = NO_STYLE

    private var buttonTextStyleId = NO_STYLE

    private val buttonDefaultShadowStyleId = R.style.AeroShadowXl

    private val defaultSpinnerSize = 24.toPx

    @ColorInt
    private var buttonBorderColor: Int = 0

    @ColorInt
    private var buttonDisableBorderColor: Int = 0

    @ColorInt
    private var buttonBackgroundColor: Int = 0

    @ColorInt
    private var buttonPressedBackgroundColor: Int = 0

    @ColorInt
    private var buttonTextColor: Int = 0

    @ColorInt
    private var buttonDisableTextColor: Int = 0

    @ColorInt
    private var buttonDisableBackgroundColor: Int = 0

    var isLoading: Boolean = false
        set(value) {
            field = value
            isClickable = !field
            titleTextView.isGone = field
            spinner.isGone = !field
        }

    var hasShadow: Boolean = false
        set(isShadowEnabled) {
            field = isShadowEnabled
            background = AeroShadowDrawableWrapper(
                buttonBackgroundDrawable,
                context,
                if (isShadowEnabled) buttonDefaultShadowStyleId else NO_STYLE
            )
        }

    private val viennaClickAnimator =
        AeroClickAnimator(this, SCALE_DOWN_FACTOR, ANIMATION_DURATION)

    private val buttonChildrenLayoutParams = LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        gravity = Gravity.CENTER
    }

    init {
        isSaveEnabled = true
        clipToOutline = false
        isClickable = true

        val topBottomPadding = resources.getDimensionPixelSize(R.dimen.dimen_16)
        val startEndPadding = resources.getDimensionPixelSize(R.dimen.dimen_32)

        setPadding(
            startEndPadding, topBottomPadding, startEndPadding, topBottomPadding
        )

        context.withStyledAttributes(attrs, R.styleable.AeroButton, defStyleAttr) {
            initialize(this)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isEnabled && isClickable) {
            viennaClickAnimator.animateOnTouch(event)
        }
        return super.onTouchEvent(event)
    }

    override fun onDetachedFromWindow() {
        viennaClickAnimator.cancel()
        super.onDetachedFromWindow()
    }

    private fun hasPressedBackgroundColor() = buttonPressedBackgroundColor != 0

    private fun initialize(typedArray: TypedArray) {
        with(typedArray) {

            buttonTitle = getString(
                R.styleable.AeroButton_buttonTitle
            )

            minimumHeight = getDimensionPixelSize(
                R.styleable.AeroButton_buttonHeight,
                0
            )

            buttonBorderRadius = getDimensionPixelSize(
                R.styleable.AeroButton_buttonBorderRadius,
                0
            )

            buttonDisableTextColor = getColor(
                R.styleable.AeroButton_buttonDisabledTextColor,
                0
            )

            buttonTextColor = getColor(
                R.styleable.AeroButton_buttonTextColor,
                0
            )

            buttonBorderWidth = getDimensionPixelSize(
                R.styleable.AeroButton_buttonBorderWidth,
                0
            )

            buttonSpinnerSize = getDimensionPixelSize(
                R.styleable.AeroButton_buttonSpinnerSize,
                defaultSpinnerSize
            )

            buttonBorderColor = getColor(
                R.styleable.AeroButton_buttonBorderColor,
                0
            )

            buttonDisableBorderColor = getColor(
                R.styleable.AeroButton_buttonDisabledBorderColor,
                0
            )

            buttonTextStyleId = getResourceId(
                R.styleable.AeroButton_buttonTextStyleId,
                0
            )

            buttonSpinnerStyleId = getResourceId(
                R.styleable.AeroButton_buttonSpinnerStyleId,
                R.style.AeroSpinnerLOrange
            )

            buttonBackgroundColor = getColor(
                R.styleable.AeroButton_buttonActiveBackground,
                TRANSPARENT_COLOR
            )

            buttonDisableBackgroundColor = getColor(
                R.styleable.AeroButton_buttonInactiveBackground,
                0
            )

            buttonBackgroundDrawable = AeroGradientDrawable().apply {
                if (buttonBorderWidth != 0) {
                    setStroke(
                        buttonBorderWidth,
                        StateListUtils.createColorStateList(
                            defaultColorInt = buttonBorderColor,
                            disabledColorInt = buttonDisableBorderColor
                        )
                    )
                }

                color = StateListUtils.createColorStateList(
                    defaultColorInt = buttonBackgroundColor,
                    disabledColorInt = buttonDisableBackgroundColor
                )

                cornerRadius = buttonBorderRadius.toFloat()
            }

            initButtonTitle()
            initSpinner()

            hasShadow = getBoolean(R.styleable.AeroButton_buttonHasShadow, false)
            isLoading = getBoolean(R.styleable.AeroButton_buttonIsLoading, false)
            isEnabled = getBoolean(R.styleable.AeroButton_buttonIsEnabled, true)

            initButtonAnimators()
        }
    }

    private fun initButtonAnimators() {
        stateListAnimator = StateListAnimator().apply {
            addState(
                intArrayOf(-android.R.attr.state_enabled),
                createButtonColorChangeAnimator(
                    buttonBackgroundColor,
                    buttonDisableBackgroundColor
                )
            )
            addState(
                intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled),
                createButtonColorChangeAnimator(
                    buttonBackgroundColor,
                    buttonPressedBackgroundColor
                )
            )
            addState(
                intArrayOf(-android.R.attr.state_pressed, android.R.attr.state_enabled),
                createButtonColorChangeAnimator(
                    buttonPressedBackgroundColor,
                    buttonBackgroundColor
                )
            )
        }
    }

    private fun initSpinner() {

        spinner = AeroSpinner(context).apply {
            id = generateViewId()
            layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER
            }
        }

        addView(spinner)
    }

    private fun initButtonTitle() {

        titleTextView = AeroTextView(
            context = context,
            defStyleRes = buttonTextStyleId
        ).apply {
            id = generateViewId()
            isDuplicateParentStateEnabled = true
            gravity = Gravity.CENTER_VERTICAL
            setTextColor(
                StateListUtils.createColorStateList(buttonTextColor, buttonDisableTextColor)
            )
            layoutParams = buttonChildrenLayoutParams
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.END
        }

        setTitle(buttonTitle)
        addView(titleTextView)
    }

    @Suppress("UNCHECKED_CAST")
    private fun createButtonColorChangeAnimator(
        @ColorInt startColorInt: Int,
        @ColorInt endColorInt: Int
    ) = ValueAnimator.ofArgb(
        startColorInt,
        endColorInt
    ).apply {
        duration = ANIMATION_DURATION
        addUpdateListener { animator ->
            if (hasPressedBackgroundColor()) {
                background.mutate().apply {
                    this as AeroShadowDrawableWrapper<GradientDrawable>
                    getDrawable().apply {
                        color = StateListUtils.createColorStateList(
                            defaultColorInt = animator.animatedValue as Int,
                            disabledColorInt = buttonDisableBackgroundColor
                        )
                    }
                }
            }
        }
    }

    fun setTitle(@StringRes titleRes: Int) {
        buttonTitle = if (titleRes != 0) resources.getString(titleRes) else null
        titleTextView.text = buttonTitle
    }

    fun setTitle(title: CharSequence?) {
        buttonTitle =
            if (isInEditMode && title.isNullOrEmpty()) EDIT_MODE_BUTTON_TITLE else title.toString()
        titleTextView.text = buttonTitle
    }

    internal fun setTitleTextColor(@ColorInt textColor: Int) {
        buttonTextColor = textColor

        titleTextView.setTextColor(
            StateListUtils.createColorStateList(
                buttonTextColor,
                buttonDisableTextColor
            )
        )
    }

    internal fun setButtonColorsAndBorderRadius(
        @ColorInt buttonBorderColor: Int = this.buttonBorderColor,
        @ColorInt buttonDisableBorderColor: Int = this.buttonDisableBorderColor,
        @ColorInt buttonBackgroundColor: Int = this.buttonBackgroundColor,
        @ColorInt buttonDisableBackgroundColor: Int = this.buttonDisableBackgroundColor,
        buttonBorderRadius: Float = this.buttonBorderRadius.toFloat()
    ) {
        buttonBackgroundDrawable = AeroGradientDrawable().apply {
            if (buttonBorderWidth != 0) {
                setStroke(
                    buttonBorderWidth,
                    StateListUtils.createColorStateList(
                        defaultColorInt = buttonBorderColor,
                        disabledColorInt = buttonDisableBorderColor
                    )
                )
            }

            color = StateListUtils.createColorStateList(
                defaultColorInt = buttonBackgroundColor,
                disabledColorInt = buttonDisableBackgroundColor
            )

            cornerRadius = buttonBorderRadius
        }
        background = buttonBackgroundDrawable
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        spinner.setStyle(
            if (enabled) buttonSpinnerStyleId else buttonDisabledSpinnerStyleId
        )
    }

    override fun onSaveInstanceState(): Parcelable =
        Bundle().apply {
            putParcelable(id.toString(), super.onSaveInstanceState())
            putBoolean(BUNDLE_IS_LOADING, isLoading)
        }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var savedState = state
        if (state is Bundle) {
            isLoading = state.getBoolean(BUNDLE_IS_LOADING)
            savedState = state.getParcelable(id.toString())
        }
        super.onRestoreInstanceState(savedState)
    }

    override fun getAccessibilityClassName(): CharSequence = Button::class.java.name

    companion object {
        private const val SCALE_DOWN_FACTOR = .95F
        private const val ANIMATION_DURATION = 300L
        private const val BUNDLE_IS_LOADING = "IS_LOADING"
        private const val EDIT_MODE_BUTTON_TITLE = "Button"
    }
}