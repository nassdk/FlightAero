package com.nassdk.featureflights.presentation.mvi

import com.nassdk.corecommon.mvi.BaseViewState
import com.nassdk.featureflights.domain.entity.FlightEntity

internal data class FlightsViewState(
    var isLoading: Boolean = false,
    var flights: List<FlightEntity> = emptyList(),
    var error: String = "",
    var offset: Int = 0,
    var isLastPage: Boolean = false
) : BaseViewState {

    companion object {
        fun retrieveDefaultState(): FlightsViewState {
            return FlightsViewState()
        }
    }
}