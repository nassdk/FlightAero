package com.nassdk.featureflights.di.module

import androidx.lifecycle.ViewModel
import com.nassdk.corecommon.di.ViewModelModule
import com.nassdk.corecommon.mapper.BaseMapper
import com.nassdk.coredi.scopes.FeatureScope
import com.nassdk.featureflights.FlightsApi
import com.nassdk.featureflights.FlightsApiImpl
import com.nassdk.featureflights.data.mapper.RTFlightsResponseMapper
import com.nassdk.featureflights.data.network.dto.RTFlightsResponseDto
import com.nassdk.featureflights.data.repo.FlightsRepositoryImpl
import com.nassdk.featureflights.domain.entity.RTFlightsEntity
import com.nassdk.featureflights.domain.repo.FlightsRepository
import com.nassdk.featureflights.presentation.mvi.FlightsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface FlightsModule {

    @Binds
    @FeatureScope
    fun bindModuleApi(impl: FlightsApiImpl): FlightsApi

    @Binds
    @FeatureScope
    fun bindRepository(impl: FlightsRepositoryImpl): FlightsRepository

    @Binds
    @FeatureScope
    fun bindMapper(impl: RTFlightsResponseMapper): BaseMapper<RTFlightsResponseDto, RTFlightsEntity>

    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(FlightsViewModel::class)
    fun provideRegistrationViewModel(viewModel: FlightsViewModel): ViewModel
}