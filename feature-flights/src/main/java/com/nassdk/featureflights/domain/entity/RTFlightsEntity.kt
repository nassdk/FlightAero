package com.nassdk.featureflights.domain.entity

internal data class RTFlightsEntity(
    val flights: List<FlightEntity>,
    val pagination: PaginationEntity,
)