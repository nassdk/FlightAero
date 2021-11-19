package com.nassdk.featureflights.data.mapper

import com.nassdk.corecommon.extensions.convert2ProperPattern
import com.nassdk.corecommon.mapper.BaseMapper
import com.nassdk.featureflights.data.network.dto.FlightDto
import com.nassdk.featureflights.data.network.dto.RTFlightsResponseDto
import com.nassdk.featureflights.domain.entity.FlightEntity
import com.nassdk.featureflights.domain.entity.FlightStatus
import com.nassdk.featureflights.domain.entity.PaginationEntity
import com.nassdk.featureflights.domain.entity.RTFlightsEntity
import javax.inject.Inject

internal class RTFlightsResponseMapper @Inject constructor() :
    BaseMapper<RTFlightsResponseDto, RTFlightsEntity> {

    override fun map(from: RTFlightsResponseDto): RTFlightsEntity {

        return RTFlightsEntity(
            pagination = PaginationEntity(
                limit = from.pagination.limit,
                offset = from.pagination.limit,
                total = from.pagination.total
            ),
            flights = mapFlights(flights = from.data)
        )
    }

    private fun mapFlights(flights: List<FlightDto>) = flights.map { flight ->
        flight.run {
            FlightEntity(
                status = FlightStatus.getBy(code = status),
                number = flight.flightInfo.number,
                arrivalTime = arrival.estimated.convert2ProperPattern().orEmpty(),
                arrivalTimezone = arrival.timezone ?: DEFAULT_TIME_ZONE_VALUE,
                departureTime = departure.estimated.convert2ProperPattern().orEmpty(),
                departureTimezone = departure.timezone ?: DEFAULT_TIME_ZONE_VALUE
            )
        }
    }

    private companion object {
        private const val DEFAULT_TIME_ZONE_VALUE = "Unknown"
    }
}