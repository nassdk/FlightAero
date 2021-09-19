package com.nassdk.corecommon.extensions

import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nassdk.corecommon.utils.AlertDialogHelper

/**
 * Extension function for androidx.fragment.app.Fragment that simplify
 * {@link android.os.Bundle} unpacking.
 * @return ReadOnlyProperty
 * Usage example:
 * val argument: Int by argumentsDelegate()
 */
inline fun <reified T> Fragment.argumentDelegate(): LazyProvider<Fragment, T> {
    return argumentDelegate { it.arguments }
}

fun Fragment.hideKeyboard(windowFlags: Int = 0) {

    val currentFocus = requireActivity().currentFocus ?: return
    ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        ?.hideSoftInputFromWindow(currentFocus.windowToken, windowFlags)
}

inline fun Fragment.alert(
    title: String = "",
    message: String = "",
    cancelable: Boolean = true,
    func: AlertDialogHelper.() -> Unit
) = AlertDialogHelper(
    context = view?.context!!,
    title = title,
    message = message,
    cancelable = cancelable
).also(func).create()
