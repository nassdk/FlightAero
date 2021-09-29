package com.nassdk.corecommon.base

import android.app.Application
import com.nassdk.corecommon.BuildConfig
import com.nassdk.corecommon.di.DaggerAppComponent
import timber.log.Timber

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

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}