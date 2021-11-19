package com.nassdk.featureflights.data.repo

import com.nassdk.corecommon.mapper.BaseMapper
import com.nassdk.featureflights.data.network.api.FlightsRestApi
import com.nassdk.featureflights.data.network.dto.RTFlightsResponseDto
import com.nassdk.featureflights.domain.entity.RTFlightsEntity
import com.nassdk.featureflights.domain.repo.FlightsRepository
import javax.inject.Inject

internal class FlightsRepositoryImpl @Inject constructor(
    private val api: FlightsRestApi,
    private val mapper: BaseMapper<RTFlightsResponseDto, RTFlightsEntity>,
) : FlightsRepository {

    override suspend fun getRealTimeFlights(offset: Int): RTFlightsEntity {
        return mapper.map(
            from = api.getRealTimeFlights(offset = offset)
        )
    }
}