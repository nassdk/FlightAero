package com.nassdk.corenavigation.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.MultiReducer
import com.github.terrakok.modo.android.AppReducer
import com.github.terrakok.modo.android.LogReducer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NavigationModule {

    @Provides
    @Singleton
    fun provideModo(context: Context): Modo {
        val reducer = if (BuildConfig.DEBUG)
            LogReducer(AppReducer(context = context, origin = MultiReducer()))
        else AppReducer(context = context, origin = MultiReducer())

        return Modo(reducer = reducer)
    }
}