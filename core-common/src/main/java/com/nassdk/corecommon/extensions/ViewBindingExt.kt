package com.nassdk.corecommon.extensions

import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.nassdk.corecommon.delegate.ViewBindingProperty
import kotlin.properties.ReadOnlyProperty

private class FragmentViewBindingProperty<F : Fragment, T : ViewBinding>(
    viewBinder: (F) -> T
) : ViewBindingProperty<F, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: F) = thisRef.viewLifecycleOwner
}

/**
 * Create new [ViewBinding] associated with the [Fragment]
 */
@JvmName("viewBindingFragment")
fun <F : Fragment, T : ViewBinding> Fragment.viewBinding(
    viewBinder: (F) -> T
): ReadOnlyProperty<F, T> {
    return FragmentViewBindingProperty(viewBinder)
}

/**
 * Create new [ViewBinding] associated with the [Fragment]
 *
 * @param vbFactory Function that create new instance of [ViewBinding]. `MyViewBinding::bind` can be used
 * @param viewProvider Provide a [View] from the Fragment. By default call [Fragment.requireView]
 */
@JvmName("viewBindingFragment")
inline fun <F : Fragment, T : ViewBinding> Fragment.viewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (F) -> View = Fragment::requireView
): ReadOnlyProperty<F, T> {
    return viewBinding { fragment: F -> vbFactory(viewProvider(fragment)) }
}

/**
 * Create new [ViewBinding] associated with the [Fragment]
 *
 * @param vbFactory Function that create new instance of [ViewBinding]. `MyViewBinding::bind` can be used
 * @param viewBindingRootId Root view's id that will be used as root for the view binding
 */
@JvmName("viewBindingFragment")
inline fun <T : ViewBinding> Fragment.viewBinding(
    crossinline vbFactory: (View) -> T,
    @IdRes viewBindingRootId: Int
): ReadOnlyProperty<Fragment, T> {
    return viewBinding(vbFactory) { fragment: Fragment ->
        fragment.requireView().findViewById(viewBindingRootId)
    }
}

private class ActivityViewBindingProperty<A : ComponentActivity, T : ViewBinding>(
    viewBinder: (A) -> T
) : ViewBindingProperty<A, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: A) = thisRef
}

/**
 * Create new [ViewBinding] associated with the [Activity][ComponentActivity] and allow customize how
 * a [View] will be bounded to the view binding.
 */
@JvmName("viewBindingActivity")
fun <A : ComponentActivity, T : ViewBinding> ComponentActivity.viewBinding(
    viewBinder: (A) -> T
): ReadOnlyProperty<A, T> {
    return ActivityViewBindingProperty(viewBinder)
}

/**
 * Create new [ViewBinding] associated with the [Activity][ComponentActivity] and allow customize how
 * a [View] will be bounded to the view binding.
 */
@JvmName("viewBindingActivity")
inline fun <A : ComponentActivity, T : ViewBinding> ComponentActivity.viewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (A) -> View
): ReadOnlyProperty<A, T> {
    return viewBinding { activity: A -> vbFactory(viewProvider(activity)) }
}

/**
 * Create new [ViewBinding] associated with the [Activity][this] and allow customize how
 * a [View] will be bounded to the view binding.
 *
 * @param vbFactory Function that create new instance of [ViewBinding]. `MyViewBinding::bind` can be used
 * @param viewBindingRootId Root view's id that will be used as root for the view binding
 */
@Suppress("unused")
@JvmName("viewBindingActivity")
inline fun <T : ViewBinding> ComponentActivity.viewBinding(
    crossinline vbFactory: (View) -> T,
    @IdRes viewBindingRootId: Int
): ReadOnlyProperty<ComponentActivity, T> {
    return viewBinding { activity: ComponentActivity ->
        vbFactory(
            activity.findViewById(
                viewBindingRootId
            )
        )
    }
}
