package com.hotchemi.featureflights.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlightDto(
    @SerialName("flight_status") val status: String,
    @SerialName("departure") val departure: DepartureDto,
    @SerialName("arrival") val arrival: DepartureDto,
    @SerialName("flight") val flightInfo: InfoFlightDto
)