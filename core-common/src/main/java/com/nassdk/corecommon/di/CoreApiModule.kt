package com.nassdk.corecommon.di

import com.nassdk.corecommon.error.ErrorHandler
import com.nassdk.corecommon.error.ErrorHandlerImpl
import com.nassdk.corecommon.resourcemanager.ResourceManager
import com.nassdk.corecommon.resourcemanager.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface CoreApiModule {

    @Binds
    @Reusable
    fun bindResourceManager(impl: ResourceManagerImpl): ResourceManager

    @Binds
    @Reusable
    fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler
}