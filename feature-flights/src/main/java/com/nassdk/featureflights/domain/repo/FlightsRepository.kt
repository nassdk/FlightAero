package com.nassdk.featureflights.domain.repo

import com.nassdk.featureflights.domain.entity.RTFlightsEntity

internal interface FlightsRepository {
    suspend fun getRealTimeFlights(offset: Int): RTFlightsEntity
}