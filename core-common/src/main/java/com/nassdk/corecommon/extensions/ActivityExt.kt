package com.nassdk.corecommon.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.nassdk.corecommon.utils.AlertDialogHelper

/**
 * Extension function for android.app.Activity that simplify
 * android.os.Bundle unpacking.
 * @return ReadOnlyProperty
 * Usage example:
 * val argument: Int by argumentsDelegate()
 */
inline fun <reified T> Activity.argumentDelegate(): LazyProvider<Activity, T> {
    return argumentDelegate { it.intent?.extras }
}

inline fun Activity.alert(
    title: String = "",
    message: String = "",
    cancelable: Boolean = true,
    func: AlertDialogHelper.() -> Unit
): AlertDialog {
    return AlertDialogHelper(
        context = this,
        title = title,
        cancelable = cancelable,
        message = message
    ).also(func).create()
}

fun FragmentActivity.hideKeyboard(windowFlags: Int = 0) {

    val currentFocus = this.currentFocus ?: return
    ContextCompat.getSystemService(this@hideKeyboard, InputMethodManager::class.java)
        ?.hideSoftInputFromWindow(currentFocus.windowToken, windowFlags)
}