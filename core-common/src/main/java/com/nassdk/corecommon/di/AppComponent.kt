package com.nassdk.corecommon.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.nassdk.corecommon.base.BaseActivity
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.corenavigation.handler.LocalCiceroneHolder
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

    fun exposeGlobalNavigator(): GlobalNavigator
    fun exposeNavigatorHolder(): NavigatorHolder
    fun exposeLocalCiceroneHolder(): LocalCiceroneHolder
}