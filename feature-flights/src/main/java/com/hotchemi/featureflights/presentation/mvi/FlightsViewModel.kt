package com.hotchemi.featureflights.presentation.mvi

import com.nassdk.corecommon.base.BaseViewModel
import javax.inject.Inject

internal class FlightsViewModel @Inject constructor(
) : BaseViewModel<FlightsViewState, FlightsViewEvent>() {

    override fun observe(event: FlightsViewEvent) = Unit
}