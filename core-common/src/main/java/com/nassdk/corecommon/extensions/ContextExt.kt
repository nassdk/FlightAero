package com.nassdk.corecommon.extensions

import android.content.Context
import com.nassdk.corecommon.base.BaseApplication
import com.nassdk.corecommon.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is BaseApplication -> appComponent
        else -> applicationContext.appComponent
    }