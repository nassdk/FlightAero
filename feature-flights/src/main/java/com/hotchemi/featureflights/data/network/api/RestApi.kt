package com.hotchemi.featureflights.data.network.api

import com.hotchemi.featureflights.data.network.dto.RTFlightsResponseDto
import retrofit2.http.GET

interface RestApi {

    @GET("flights")
    suspend fun getRealTimeFlights(): RTFlightsResponseDto
}