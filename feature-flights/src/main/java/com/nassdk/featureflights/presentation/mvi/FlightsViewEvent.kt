package com.nassdk.featureflights.presentation.mvi

import com.nassdk.corecommon.mvi.BaseViewEvent

internal sealed class FlightsViewEvent : BaseViewEvent {
    object LoadMore : FlightsViewEvent()
}