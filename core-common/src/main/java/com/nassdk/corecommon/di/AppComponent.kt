package com.nassdk.corecommon.di

import android.content.Context
import com.nassdk.corecommon.base.BaseActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: BaseActivity)
}