package com.nassdk.featureflights.presentation.ui

import com.kaspersky.kaspresso.screens.KScreen
import com.nassdk.featureflights.domain.entity.FlightEntity
import com.nassdk.featureflights.test.R
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerView

object FlightsScreen : KScreen<FlightsScreen>() {

    override val layoutId: Int = R.layout.screen_flights
    override val viewClass: Class<*> = FlightsFragment::class.java

    val shimmerLoading = KView { withId(R.id.shimmerLoading) }
    val recyclerFlights = KRecyclerView(
        builder = {
            withId(R.id.rvFlights)
        }, itemTypeBuilder = {
            FlightEntity::class.java
        }
    )
}