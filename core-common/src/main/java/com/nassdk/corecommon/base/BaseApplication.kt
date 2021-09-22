package com.nassdk.corecommon.base

import android.app.Application
import com.nassdk.corecommon.di.DaggerAppComponent

abstract class BaseApplication : Application() {

    companion object {
        lateinit var app: BaseApplication
    }

    init {
        app = this
    }

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(context = applicationContext)
    }
}