package com.nassdk.corecommon.di

import com.nassdk.corenavigation.di.NavigationModule
import com.nassdk.corenetwork.di.NetworkModule
import dagger.Module

@Module(includes = [NetworkModule::class, CoreApiModule::class, NavigationModule::class])
internal interface AppModule