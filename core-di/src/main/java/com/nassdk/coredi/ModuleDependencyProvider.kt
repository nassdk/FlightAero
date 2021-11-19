package com.nassdk.coredi

fun interface ModuleDependenciesProvider<out T> {
    fun getDependencies(): T
}