package com.nassdk.corenavigation.handler

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalCiceroneHolder @Inject constructor() {

    private val containers by lazy {
        hashMapOf<String, Cicerone<Router>>()
    }

    fun getCicerone(containerTag: String): Cicerone<Router> =
        containers.getOrPut(key = containerTag) {
            Cicerone.create()
        }
}