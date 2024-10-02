package com.lmr.appmodule.vendor.utils

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
object BindingAdapters {
    @BindingAdapter("android:setHexBackGroundColor")
    @JvmStatic
    fun setHexBackGroundColor(view: View, colorHex: String?) {
        if (!colorHex.isNullOrEmpty()) {
            val colorInt = Color.parseColor(colorHex)
            view.setBackgroundColor(colorInt)
        }
    }

}