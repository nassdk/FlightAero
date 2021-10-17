package com.nassdk.featureflow.domain.entity

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.nassdk.featureflow.R

internal enum class FlowTab(
    @IdRes val idRes: Int,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    @StringRes val containerTag: Int
) {
    FLIGHTS(
        idRes = R.id.flow_tab_flights_id,
        titleRes = R.string.flow_tab_flights_title,
        iconRes = R.drawable.icv_flow_tab_flights,
        containerTag = R.string.flow_tab_flights_container_name
    ),
    PROFILE(
        idRes = R.id.flow_tab_profile_id,
        titleRes = R.string.flow_tab_flights_title,
        iconRes = R.drawable.icv_flow_tab_profile,
        containerTag = R.string.flow_tab_profile_container_name
    );

    companion object {
        fun getById(@IdRes id: Int): FlowTab {
            values().forEach {
                if (it.idRes == id) {
                    return it
                }
            }
            throw IllegalArgumentException("tab not found fo id $id")
        }
    }
}