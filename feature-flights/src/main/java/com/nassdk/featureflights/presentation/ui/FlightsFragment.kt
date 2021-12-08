package com.nassdk.featureflights.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nassdk.corecommon.base.BaseMviFragment
import com.nassdk.corecommon.decorators.SpacingItemDecoration
import com.nassdk.corecommon.extensions.executeWithDelayedTransition
import com.nassdk.corecommon.extensions.isVisible
import com.nassdk.corecommon.extensions.uiLazy
import com.nassdk.corecommon.pagination.PaginationListener
import com.nassdk.featureflights.FlightsFeature
import com.nassdk.featureflights.R
import com.nassdk.featureflights.databinding.ScreenFlightsBinding
import com.nassdk.featureflights.domain.entity.FlightEntity
import com.nassdk.featureflights.presentation.mvi.FlightsViewEvent
import com.nassdk.featureflights.presentation.mvi.FlightsViewModel
import com.nassdk.featureflights.presentation.mvi.FlightsViewState
import javax.inject.Inject

internal class FlightsFragment : BaseMviFragment<FlightsViewState, FlightsViewEvent,
        FlightsViewModel>(layoutResId = R.layout.screen_flights) {

    companion object {
        fun newInstance() = FlightsFragment()

        private const val VERTICAL_SPACING = 12
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel by viewModels<FlightsViewModel>(factoryProducer = { viewModelFactory })

    override fun setupInjection() = FlightsFeature.getComponent().inject(this)
    override fun onFinalDestroy() = FlightsFeature.destroyModuleGraph()

    private val viewBinding by viewBinding(ScreenFlightsBinding::bind)

    private val flightsAdapter by uiLazy {
        FlightsAdapter()
    }

    private var isLastPage = false
    private var isLoading = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setSecondaryStatusBarColor()
    }

    override fun setupUi() {

        val layoutManager = LinearLayoutManager(context)

        with(viewBinding.rvFlights) {
            this.layoutManager = layoutManager
            adapter = flightsAdapter
            addItemDecoration(SpacingItemDecoration(margin = VERTICAL_SPACING))
            addOnScrollListener(
                object : PaginationListener(layoutManager = layoutManager) {
                    override fun loadMoreItems() {
                        viewModel.perform(FlightsViewEvent.LoadMore)
                    }

                    override fun isLastPage(): Boolean {
                        return isLastPage
                    }

                    override fun isLoading(): Boolean {
                        return isLoading
                    }
                }
            )
        }
    }

    override fun render(state: FlightsViewState) {

        renderLoading(loading = state.isLoading)
        renderError(error = state.error)
        renderFlightData(flights = state.flights)
        renderLastPageState(isLastPage = state.isLastPage)
    }

    private fun renderLoading(loading: Boolean) {
        isLoading = loading

        with(viewBinding) {
            root.executeWithDelayedTransition {
                rvFlights.isVisible(!loading)
                shimmerLoading.root.isVisible(loading)
            }
        }
    }

    private fun renderError(error: String?) {
        if (error.isNullOrBlank()) return
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    private fun renderFlightData(flights: List<FlightEntity>) {
        flightsAdapter.add(items = flights)
    }

    private fun renderLastPageState(isLastPage: Boolean) {
        this.isLastPage = isLastPage
    }
}