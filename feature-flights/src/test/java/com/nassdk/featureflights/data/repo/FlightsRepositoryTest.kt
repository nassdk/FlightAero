package com.nassdk.featureflights.data.repo

import com.nassdk.corecommon.mapper.BaseMapper
import com.nassdk.featureflights.data.network.api.FlightsRestApi
import com.nassdk.featureflights.data.network.dto.PaginationDto
import com.nassdk.featureflights.data.network.dto.RTFlightsResponseDto
import com.nassdk.featureflights.domain.entity.PaginationEntity
import com.nassdk.featureflights.domain.entity.RTFlightsEntity
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

internal class FlightsRepositoryTest {

    private val mockedEntity = RTFlightsEntity(
        pagination = PaginationEntity(limit = 0, offset = 0, total = 0), flights = emptyList()
    )

    private val restApi: FlightsRestApi = mock()
    private val mapper: BaseMapper<RTFlightsResponseDto, RTFlightsEntity> = mock {
        on { map(any()) }.thenReturn(mockedEntity)
    }

    private val repository = FlightsRepositoryImpl(api = restApi, mapper = mapper)

    @Test
    fun `when get flights - then success`() {

        //Given
        val currentOffset = 20
        val mockedDto = RTFlightsResponseDto(
            pagination = PaginationDto(limit = 0, offset = 0, total = 0), data = emptyList()
        )
        given { runBlocking { restApi.getRealTimeFlights(offset = currentOffset) } }
            .willReturn(mockedDto)

        //When
        val result = runBlocking { repository.getRealTimeFlights(offset = currentOffset) }

        //Then
        assert(result == mockedEntity)
    }
}