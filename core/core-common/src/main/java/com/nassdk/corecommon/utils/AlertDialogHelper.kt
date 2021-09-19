package com.nassdk.corecommon.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.nassdk.corecommon.R
import com.nassdk.corecommon.extensions.isVisible
import com.nassdk.corecommon.extensions.uiLazy

/**
 * Шаблон для вызова алертов в Активити и Фрагментах.
 *
 * P.S. Смахивает при вызове на декларативщину = каеф
 */

class AlertDialogHelper(
    context: Context,
    title: CharSequence?,
    message: CharSequence?,
    private var cancelable: Boolean = true
) {

    private val dialogView: View by uiLazy {
        LayoutInflater.from(context).inflate(R.layout.layout_dialog, null)
    }

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(dialogView)

    private val title: TextView by uiLazy {
        dialogView.findViewById(R.id.dialogTitleText)
    }

    private val message: TextView by uiLazy {
        dialogView.findViewById(R.id.dialogMessageTest)
    }

    private val positiveButton: AppCompatButton by uiLazy {
        dialogView.findViewById(R.id.dialogBtnApply)
    }

    private val negativeButton: AppCompatButton by uiLazy {
        dialogView.findViewById(R.id.dialogBtnCancel)
    }

    private var dialog: AlertDialog? = null

    init {
        this.title.text = title
        this.message.text = message
    }

    fun positiveButton(@StringRes text: Int, clickListener: (() -> Unit)? = null) {
        with(positiveButton) {
            this.text = builder.context.getString(text)
            setClickListenerToDialogButton(clickListener)
        }
    }

    fun positiveButton(text: CharSequence, clickListener: (() -> Unit)? = null) {
        with(positiveButton) {
            this.text = text
            setClickListenerToDialogButton(clickListener)
        }
    }

    fun negativeButton(@StringRes text: Int, clickListener: (() -> Unit)? = null) {
        with(negativeButton) {
            this.text = builder.context.getString(text)
            setClickListenerToDialogButton(clickListener)
        }
    }

    fun negativeButton(text: CharSequence, clickListener: (() -> Unit)? = null) {
        with(negativeButton) {
            this.text = text
            setClickListenerToDialogButton(clickListener)
        }
    }

    fun onCancel(func: () -> Unit) {
        builder.setOnCancelListener { func() }
    }

    fun create(): AlertDialog {

        title.isVisible(title.text.isNullOrEmpty().not())
        message.isVisible(message.text.isNullOrEmpty().not())
        positiveButton.isVisible(positiveButton.text.isNullOrEmpty().not())
        negativeButton.isVisible(negativeButton.text.isNullOrEmpty().not())

//        title.setTextColor(ContextCompat.getColor(dialogView.context, R.color.color_black))
//        message.setTextColor(ContextCompat.getColor(dialogView.context, R.color.color_black))

        dialog = builder
            .setCancelable(cancelable)
            .create()

        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        return dialog!!
    }

    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
}
