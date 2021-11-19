package com.nassdk.featureflights.domain.entity

internal data class FlightEntity(
    val status: FlightStatus,
    val number: String,
    val arrivalTimezone: String,
    val arrivalTime: String,
    val departureTimezone: String,
    val departureTime: String,
)
