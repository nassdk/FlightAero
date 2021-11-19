package com.nassdk.featureflights.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nassdk.corecommon.base.BaseAdapter
import com.nassdk.corecommon.extensions.setDrawableTint
import com.nassdk.featureflights.R
import com.nassdk.featureflights.databinding.ItemFlightBinding
import com.nassdk.featureflights.domain.entity.FlightEntity

internal class FlightsAdapter : BaseAdapter<ItemFlightBinding, FlightEntity>() {

    override val viewBindingInflate: (
        inflater: LayoutInflater,
        viewGroup: ViewGroup,
        attachToParent: Boolean,
    ) -> ItemFlightBinding = ItemFlightBinding::inflate

    override fun BaseViewHolder<ItemFlightBinding>.onBind(entity: FlightEntity) {

        with(itemBinding) {

            number.text = context.getString(R.string.flights_flight_number, entity.number)
            arrivalCity.text = entity.arrivalTimezone
            departureCity.text = entity.departureTimezone
            arrivalTime.text = entity.arrivalTime
            departureTime.text = entity.departureTime

            status.text = context.getString(entity.status.value)
            status.setTextColor(context.getColor(entity.status.tint))
            status.setDrawableTint(startDrawableTint = entity.status.tint)
        }
    }
}