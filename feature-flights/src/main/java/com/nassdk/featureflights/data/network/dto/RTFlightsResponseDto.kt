package com.nassdk.featureflights.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class RTFlightsResponseDto(
    val pagination: PaginationDto,
    val data: List<FlightDto>,
)