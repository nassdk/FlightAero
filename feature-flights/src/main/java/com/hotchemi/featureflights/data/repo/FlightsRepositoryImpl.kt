package com.hotchemi.featureflights.data.repo

import com.hotchemi.featureflights.data.network.api.RestApi
import com.hotchemi.featureflights.data.network.dto.RTFlightsResponseDto
import com.hotchemi.featureflights.domain.repo.FlightsRepository
import javax.inject.Inject

class FlightsRepositoryImpl @Inject constructor(
    private val api: RestApi
) : FlightsRepository {

    override suspend fun getRealTimeFlights(): RTFlightsResponseDto {
        return api.getRealTimeFlights()
    }
}