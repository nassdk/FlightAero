package com.nassdk.featureflights.presentation.mvi

import com.nassdk.corecommon.mvi.BaseViewState
import com.nassdk.featureflights.domain.entity.FlightEntity

internal data class FlightsViewState(
    val isLoading: Boolean,
    val flights: List<FlightEntity>,
    val error: String? = null,
    val offset: Int,
    val isLastPage: Boolean,
) : BaseViewState {

    companion object {
        fun retrieveDefaultState(): FlightsViewState {
            return FlightsViewState(
                isLoading = false,
                flights = emptyList(),
                error = null,
                offset = 0,
                isLastPage = false
            )
        }
    }
}