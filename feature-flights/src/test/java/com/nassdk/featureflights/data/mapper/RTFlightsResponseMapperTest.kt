package com.nassdk.featureflights.data.mapper

import com.nassdk.corecommon.mapper.BaseMapper
import com.nassdk.featureflights.data.network.dto.PaginationDto
import com.nassdk.featureflights.data.network.dto.RTFlightsResponseDto
import com.nassdk.featureflights.domain.entity.PaginationEntity
import com.nassdk.featureflights.domain.entity.RTFlightsEntity
import org.junit.Test

internal class RTFlightsResponseMapperTest {

    private val mapper: BaseMapper<RTFlightsResponseDto, RTFlightsEntity> by lazy {
        RTFlightsResponseMapper()
    }

    @Test
    fun `when map - then success`() {

        //Given
        val dto = RTFlightsResponseDto(
            pagination = PaginationDto(limit = 0, offset = 0, total = 0), data = emptyList()
        )

        val entity = RTFlightsEntity(
            pagination = PaginationEntity(limit = 0, offset = 0, total = 0), flights = emptyList()
        )

        //When
        val result = mapper.map(from = dto)

        //Then
        assert(result == entity)
    }

    @Test
    fun `when map - then error`() {

        //Given
        val dto = RTFlightsResponseDto(
            pagination = PaginationDto(limit = 0, offset = 0, total = 0), data = emptyList()
        )

        val entity = RTFlightsEntity(
            pagination = PaginationEntity(limit = 1, offset = 0, total = 0), flights = emptyList()
        )

        //When
        val result = mapper.map(from = dto)

        //Then
        assert(result != entity)
    }
}