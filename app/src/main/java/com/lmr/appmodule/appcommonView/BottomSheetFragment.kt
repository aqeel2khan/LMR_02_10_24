package com.lmr.appmodule.appcommonView
import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lmr.R

class BottomSheetFragment : BottomSheetDialogFragment() {
    var viewLayout: View? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialog)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        dialog.setContentView(view)
        // Set the height of the dialog
//        val bottomSheet = dialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
//        val behavior = BottomSheetBehavior.from(bottomSheet!!)
//        behavior.peekHeight = resources.displayMetrics.heightPixels / 2
        return dialog
    }
}