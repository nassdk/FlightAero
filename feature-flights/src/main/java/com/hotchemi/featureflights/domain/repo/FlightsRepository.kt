package com.hotchemi.featureflights.domain.repo

import com.hotchemi.featureflights.data.network.dto.RTFlightsResponseDto

interface FlightsRepository {
    suspend fun getRealTimeFlights(): RTFlightsResponseDto
}