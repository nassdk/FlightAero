package com.nassdk.corecommon.di

import com.nassdk.corenavigation.di.NavigationModule
import dagger.Module

@Module(includes = [CoreApiModule::class, NavigationModule::class])
internal interface AppModule