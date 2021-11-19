package com.nassdk.corenetwork.di

import com.nassdk.coredi.scopes.NetworkScope
import com.nassdk.corenetwork.NetworkApi
import com.nassdk.corenetwork.NetworkApiImpl
import dagger.Component

@NetworkScope
@Component(modules = [NetworkModule::class])
internal interface NetworkComponent {
    @Component.Factory
    interface Factory {

        fun create(): NetworkComponent
    }

    fun inject(apiImpl: NetworkApiImpl)

    fun moduleApi(): NetworkApi
}