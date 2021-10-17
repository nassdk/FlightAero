package com.hotchemi.featureflights.presentation.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hotchemi.featureflights.FlightsFeature
import com.hotchemi.featureflights.R
import com.hotchemi.featureflights.databinding.ScreenFlightsBinding
import com.hotchemi.featureflights.presentation.mvi.FlightsViewEvent
import com.hotchemi.featureflights.presentation.mvi.FlightsViewModel
import com.hotchemi.featureflights.presentation.mvi.FlightsViewState
import com.nassdk.corecommon.base.BaseMviFragment
import javax.inject.Inject

internal class FlightsFragment : BaseMviFragment<FlightsViewState, FlightsViewEvent,
        FlightsViewModel>(layoutResId = R.layout.screen_flights) {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel by viewModels<FlightsViewModel>(factoryProducer = { viewModelFactory })

    override fun setupInjection() = FlightsFeature.getComponent().inject(this)
    override fun onFinalDestroy() = FlightsFeature.destroyModuleGraph()

    private val viewBinding by viewBinding(ScreenFlightsBinding::bind)

    override fun render(state: FlightsViewState) = Unit
}