package com.nassdk.coreimpl.di

import com.nassdk.coreapi.error.ErrorHandler
import com.nassdk.coreapi.resourcemanager.ResourceManager
import com.nassdk.coreimpl.error.ErrorHandlerImpl
import com.nassdk.coreimpl.resourcemanager.ResourceManagerImpl
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