package com.nassdk.featureflow.domain.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.nassdk.featureflow.R

internal enum class FlowTab(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val position: Int,
) {
    FLIGHTS(
        titleRes = R.string.flow_tab_flights_title,
        iconRes = R.drawable.icv_flow_tab_flights,
        position = 0
    ),
    PROFILE(
        titleRes = R.string.flow_tab_profile_title,
        iconRes = R.drawable.icv_flow_tab_profile,
        position = 1
    );

    companion object {
        fun getBy(position: Int): FlowTab {
            values().forEach {
                if (it.position == position) {
                    return it
                }
            }
            throw IllegalArgumentException("tab not found for position $position")
        }
    }
}