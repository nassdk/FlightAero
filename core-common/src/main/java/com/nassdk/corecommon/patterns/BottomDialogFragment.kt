package com.nassdk.corecommon.patterns

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nassdk.corecommon.base.BaseFragment
import com.nassdk.coreui.R
import com.nassdk.coreui.external.common.BottomDialogParams
import com.nassdk.coreui.external.common.PEEK_HEIGHT_UNDEFINED

open class BottomDialogFragment : BottomSheetDialogFragment() {

    companion object {
        private const val FRAGMENT_MODE_ARG = "fragment_mode_arg"

        fun newInstance(bottomDialogParams: BottomDialogParams = BottomDialogParams()): BottomDialogFragment {
            return BottomDialogFragment().apply {
                arguments = bundleOf(
                    FRAGMENT_MODE_ARG to bottomDialogParams
                )
            }
        }
    }

    var childFragmentTag: String? = null
        private set
    private val bottomDialogParams by lazy {
        arguments?.getParcelable(FRAGMENT_MODE_ARG) ?: BottomDialogParams()
    }

    var createFragmentFunction: (() -> Fragment)? = null
    private var onDismissListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dlg = super.onCreateDialog(savedInstanceState)

        dlg.setOnShowListener { dialog ->

            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<View>(R.id.design_bottom_sheet)
                        as? FrameLayout?
            if (bottomSheet != null) {
                val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
                bottomSheetBehavior.state = bottomDialogParams.state
                if (bottomDialogParams.peekHeightPx != PEEK_HEIGHT_UNDEFINED) {
                    bottomSheetBehavior.setPeekHeight(bottomDialogParams.peekHeightPx, false)
                }
            }
        }

        return dlg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (bottomDialogParams.isAdjustResize) {
            dialog?.window?.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
            )
        }
        return inflater.inflate(R.layout.layout_bottom_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = createFragmentFunction?.invoke() ?: return

        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        childFragmentTag = bottomDialogParams.dialogTag ?: fragment::class.java.name
        fragmentTransaction.replace(R.id.fmt_container, fragment, childFragmentTag)
        fragmentTransaction.commit()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
        (childFragmentManager.findFragmentByTag(childFragmentTag) as? BaseFragment)
            ?.onFinalDestroy()
    }

    fun setOnDismissListener(listener: () -> Unit) {
        onDismissListener = listener
    }

}