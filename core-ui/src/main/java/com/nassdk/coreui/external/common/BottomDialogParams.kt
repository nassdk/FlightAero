package com.nassdk.coreui.external.common

import android.os.Parcelable
import androidx.annotation.Px
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.parcelize.Parcelize

@Parcelize
data class BottomDialogParams(
    /**
     * Adjust windows
     */
    val isAdjustResize: Boolean = false,
    /**
     * PeekHeight for BottomSheetBehaviour
     */
    @Px
    val peekHeightPx: Int = PEEK_HEIGHT_UNDEFINED,
    /**
     * State for BottomSheetBehavior
     */
    @BottomSheetBehavior.State
    val state: Int = BottomSheetBehavior.STATE_EXPANDED,

    val dialogTag: String? = null
) : Parcelable {
    companion object {
        val DEFAULT = BottomDialogParams()
    }
}

const val PEEK_HEIGHT_UNDEFINED = Int.MAX_VALUE