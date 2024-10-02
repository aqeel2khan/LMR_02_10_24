package com.lmr.appmodule.vendor.model

import android.text.SpannableStringBuilder

data class VenueMoreDetailsDataItem(
    val foodMenu: String?,
    val listOfSubMenu: ArrayList<SubFoodMenu>?,
    val subMenu: SpannableStringBuilder?
)

data class SubFoodMenu(
    val foodSubMenu: String?,
    val isAvailable: Boolean
)