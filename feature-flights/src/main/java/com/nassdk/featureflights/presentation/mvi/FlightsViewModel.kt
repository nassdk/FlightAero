package com.nassdk.featureflights.presentation.mvi

import androidx.lifecycle.viewModelScope
import com.nassdk.corecommon.base.BaseViewModel
import com.nassdk.featureflights.domain.repo.FlightsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

internal class FlightsViewModel @Inject constructor(
    private val repository: FlightsRepository,
) : BaseViewModel<FlightsViewState, FlightsViewEvent>(
    initialState = FlightsViewState.retrieveDefaultState()
) {

    init {
        fetchFlights()
    }

    private fun fetchFlights() {

        updateState {
            copy(isLoading = true)
        }

        viewModelScope.launch {

            try {
                val rtFlightResponse =
                    repository.getRealTimeFlights(offset = viewState.first().offset)
                updateState {
                    copy(
                        flights = rtFlightResponse.flights,
                        offset = offset + rtFlightResponse.flights.size,
                        isLastPage = rtFlightResponse.pagination.offset % DEFAULT_PAGINATION_LIMIT != 0
                    )
                }
            } catch (e: Exception) {
                updateState {
                    copy(error = e.localizedMessage.orEmpty())
                }
            } finally {
                updateState { copy(isLoading = false) }
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