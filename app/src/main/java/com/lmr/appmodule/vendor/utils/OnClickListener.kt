package com.lmr.appmodule.vendor.utils

import android.view.View

interface OnClickListener {
    fun <T> onClick(view: View, item: T)
}