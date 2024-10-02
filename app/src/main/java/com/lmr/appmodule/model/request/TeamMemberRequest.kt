package com.lmr.appmodule.model.request

import com.google.gson.annotations.SerializedName

data class TeamMemberRequest(
    @SerializedName("teamMemberID")
    val teamMemberID: Int? = null,

    @SerializedName("eventOrganizerID")
    val eventOrganizerID: Int? = null,

    @SerializedName("firstName")
    val firstName: String? = null,

    @SerializedName("lastName")
    val lastName: String? = null,

    @SerializedName("mobileNumber")
    val mobileNumber: String? = null
)

data class TicketDetailsModel(
    @SerializedName("ticketDetailsID")
    val ticketDetailsID: Int? = null,

    @SerializedName("eventID")
    val eventID: Int? = null,

    @SerializedName("eventPaidType")
    val eventPaidType: Int? = null,

    @SerializedName("lstticketingdetailsrequest")
    val ticketingDetailsRequestList: List<String>? = null
)