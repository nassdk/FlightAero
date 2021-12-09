package com.nassdk.flightaero.screen.flights

import com.kaspersky.kaspresso.screens.KScreen
import com.nassdk.featureflights.presentation.ui.FlightsFragment
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView

object FlightsScreen : KScreen<FlightsScreen>() {

    override val layoutId: Int = R.layout.screen_flights
    val shimmerLoading = KView { withId(R.id.shimmerLoading) }
    val recyclerFlights = KRecyclerView(
        builder = {
            withId(R.id.rvFlights)
        }, itemTypeBuilder = {}
    )
}