package com.nassdk.corenavigation.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.nassdk.corenavigation.globalnavigator.GlobalNavigator
import com.nassdk.corenavigation.globalnavigator.GlobalNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindGlobalNavigator(impl: GlobalNavigatorImpl): GlobalNavigator

    companion object {

        private val cicerone = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(): Router {
            return cicerone.router
        }

        @Provides
        @Singleton
        fun provideNavigatorHolder(): NavigatorHolder {
            return cicerone.getNavigatorHolder()
        }
    }
}