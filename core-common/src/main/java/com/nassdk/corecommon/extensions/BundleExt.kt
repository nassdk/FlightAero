package com.nassdk.corecommon.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

/**
 * Function that simplify android.os.Bundle unpacking.
 * @param provideArguments block function, that expect android.os.Bundle from object,
 * Usage example:
 * private val wrapper = { f: Fragment -> a.arguments }
 * @return ReadOnlyProperty, Usage example:
 * val argument: Int by argumentsDelegate(wrapper)
 */
inline fun <F, reified T> argumentDelegate(
    crossinline provideArguments: (F) -> Bundle?
): LazyProvider<F, T> =
    object : LazyProvider<F, T> {

        override fun provideDelegate(thisRef: F, prop: KProperty<*>) =
            lazy {
                val bundle = provideArguments(thisRef)
                bundle?.get(prop.name) as T
            }
    }

interface LazyProvider<A, T> {
    operator fun provideDelegate(thisRef: A, prop: KProperty<*>): Lazy<T>
}

inline fun <T : Fragment> T.withArgs(
    argsBuilder: Bundle.() -> Unit
): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
