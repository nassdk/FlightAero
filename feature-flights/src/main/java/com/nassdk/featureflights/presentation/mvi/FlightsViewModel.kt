package com.nassdk.featureflights.presentation.mvi

import com.nassdk.corecommon.base.BaseViewModel
import com.nassdk.corecommon.coroutines.CoroutinesDispatcherProvider
import com.nassdk.featureflights.domain.repo.FlightsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.first

internal class FlightsViewModel @Inject constructor(
    private val repository: FlightsRepository,
    dispatcherProvider: CoroutinesDispatcherProvider = CoroutinesDispatcherProvider(),
) : BaseViewModel<FlightsViewState, FlightsViewEvent>(
    initialState = FlightsViewState.retrieveDefaultState(),
    dispatcherProvider = dispatcherProvider
) {

    init {
        fetchFlights()
    }

    private fun fetchFlights() {

        updateState {
            copy(isLoading = true)
        }

        launchIOCoroutine {

            val rtFlightResponse =
                repository.getRealTimeFlights(offset = viewState.first().offset)

            updateState {
                copy(
                    flights = rtFlightResponse.flights,
                    offset = offset + rtFlightResponse.flights.size,
                    isLastPage = rtFlightResponse.pagination.offset % DEFAULT_PAGINATION_LIMIT != 0,
                    isLoading = false
                )
            }

        }
    }

    override fun observe(event: FlightsViewEvent) {

        when (event) {
            is FlightsViewEvent.LoadMore -> fetchFlights()
        }
    }

    private companion object {
        private const val DEFAULT_PAGINATION_LIMIT = 20
    }
}