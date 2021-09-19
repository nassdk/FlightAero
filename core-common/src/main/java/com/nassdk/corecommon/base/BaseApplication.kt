package com.nassdk.corecommon.base

import android.app.Application
import com.nassdk.corecommon.di.DaggerAppComponent

abstract class BaseApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(context = applicationContext)
    }
}