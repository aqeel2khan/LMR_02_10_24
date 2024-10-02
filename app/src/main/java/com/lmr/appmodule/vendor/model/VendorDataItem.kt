package com.lmr.appmodule.vendor.model

data class VendorDataItem(
    val vendorLabel: String?,
    val vendorName: String?,
    val vendorType: String?,
    val vendorCount: Int?,
    val isBackGround: Boolean?,
    val backGroundColor: String?,
    val backGroundImage: String?,
    val vendorEventsList: ArrayList<VendorEventDataItem>?
)