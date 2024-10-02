package com.lmr.appmodule.model.request

import com.google.gson.annotations.SerializedName

data class TicketingDetailsRequest(
    @SerializedName("ticketingDetailsID")
    val ticketingDetailsID: Int? = null,

    @SerializedName("ticketDetailsID")
    val ticketDetailsID: Int? = null,

    @SerializedName("seatName")
    val seatName: String? = null,

    @SerializedName("availableQuantity")
    val availableQuantity: Int? = null,

    @SerializedName("bookingStartDate")
    val bookingStartDate: String? = null,

    @SerializedName("bookingEndDate")
    val bookingEndDate: String? = null,

    @SerializedName("bookingStartTime")
    val bookingStartTime: String? = null,

    @SerializedName("bookingEndTime")
    val bookingEndTime: String? = null
)

data class TicketDetailsRequest(
    @SerializedName("ticketDetailsID")
    val ticketDetailsID: Int? = null,

    @SerializedName("eventID")
    val eventID: Int? = null,

    @SerializedName("eventPaidType")
    val eventPaidType: Int? = null,

    @SerializedName("lstticketingdetailsrequest")
    val ticketingDetailsRequestList: List<String>? = null
)