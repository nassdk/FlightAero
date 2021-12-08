package com.nassdk.featureflights.presentation.mvi

import com.nassdk.corecommon.mvi.BaseViewState
import com.nassdk.featureflights.domain.entity.FlightEntity

internal data class FlightsViewState(
    val isLoading: Boolean = false,
    val flights: List<FlightEntity> = emptyList(),
    val error: String = "",
    val offset: Int = 0,
    val isLastPage: Boolean = false,
) : BaseViewState {

    companion object {
        fun retrieveDefaultState(): FlightsViewState {
            return FlightsViewState()
        }
    }
}