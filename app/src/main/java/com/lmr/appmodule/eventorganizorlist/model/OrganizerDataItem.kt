package com.lmr.appmodule.eventorganizorlist.model

//import com.lmr.appmodule.eventorganizorlist.VendorEventDataItem

data class OrganizerDataItem(
    var bankName: String?,
    var bankAddress: String?,
    var bankUpcomingEventCount: String?,
    var vendorCount: Int?,
    var isBackGround: Boolean?,
    var backGroundColor: String?,
    var backGroundImage: Int?,

//    val vendorEventsList: ArrayList<VendorEventDataItem>?
)