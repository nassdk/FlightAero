package com.nassdk.coredi.scopes

fun interface ModuleDependenciesProvider<out T> {
    fun getDependencies(): T
}