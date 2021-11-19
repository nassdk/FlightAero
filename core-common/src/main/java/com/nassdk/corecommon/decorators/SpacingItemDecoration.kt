package com.nassdk.corecommon.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nassdk.coreui.external.helpers.toPx

class SpacingItemDecoration(
    private val margin: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val oneSideInnerDivider = (margin / 2).toPx

        with(outRect) {
            top = oneSideInnerDivider
            bottom = oneSideInnerDivider
        }
    }
}