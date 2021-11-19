package com.nassdk.featureflights.data.network.api

import com.nassdk.featureflights.data.network.dto.RTFlightsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightsRestApi {

    @GET("flights")
    suspend fun getRealTimeFlights(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
    ): RTFlightsResponseDto
}