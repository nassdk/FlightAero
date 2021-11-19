package com.nassdk.featureflights.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class DepartureDto(
    val timezone: String?,
    val estimated: String
)
