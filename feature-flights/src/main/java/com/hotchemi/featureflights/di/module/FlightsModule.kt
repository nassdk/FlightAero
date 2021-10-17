package com.hotchemi.featureflights.di.module

import androidx.lifecycle.ViewModel
import com.hotchemi.featureflights.FlightsApi
import com.hotchemi.featureflights.FlightsApiImpl
import com.hotchemi.featureflights.data.repo.FlightsRepositoryImpl
import com.hotchemi.featureflights.domain.repo.FlightsRepository
import com.hotchemi.featureflights.presentation.mvi.FlightsViewModel
import com.hotchemi.featureflights.presentation.navigation.Router
import com.hotchemi.featureflights.presentation.navigation.RouterImpl
import com.nassdk.corecommon.di.ViewModelModule
import com.nassdk.coredi.scopes.FeatureScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal abstract class FlightsModule {

    @Binds
    @FeatureScope
    abstract fun bindModuleApi(impl: FlightsApiImpl): FlightsApi

    @Binds
    @FeatureScope
    abstract fun bindRouter(impl: RouterImpl): Router

    @Binds
    @FeatureScope
    abstract fun bindRepository(impl: FlightsRepositoryImpl): FlightsRepository

    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(FlightsViewModel::class)
    abstract fun provideRegistrationViewModel(viewModel: FlightsViewModel): ViewModel
}