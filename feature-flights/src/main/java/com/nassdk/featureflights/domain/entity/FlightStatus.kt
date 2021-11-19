package com.nassdk.featureflights.domain.entity

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.nassdk.featureflights.R

internal enum class FlightStatus(
    @StringRes val value: Int,
    @ColorRes val tint: Int,
    val code: String,
) {
    InFlight(
        value = R.string.flights_status_name_in_flight,
        tint = R.color.flight_active_tint,
        code = "active"
    ),

    Canceled(
        value = R.string.flights_status_name_canceled,
        tint = R.color.flight_canceled_tint,
        code = "cancelled"
    ),

    Scheduled(
        value = R.string.flights_status_name_scheduled,
        tint = R.color.flight_scheduled_tint,
        code = "scheduled"
    ),

    Landed(
        value = R.string.flights_status_name_landed,
        tint = R.color.flight_landed_tint,
        code = "landed"
    );

    companion object {
        fun getBy(code: String): FlightStatus {
            values().forEach {
                if (it.code == code) {
                    return it
                }
            }
            throw IllegalArgumentException("flight status with code $code not found")
        }
    }
}
