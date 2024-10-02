package com.lmr.appmodule.model.request

import com.google.gson.annotations.SerializedName

data class EventOrganizerRequest(
    @SerializedName("eventOrganizerID")
    val eventOrganizerID: Int? = null,

    @SerializedName("eventID")
    val eventID: Int? = null,

    @SerializedName("eventOrganizerName")
    val eventOrganizerName: String? = null,

    @SerializedName("eventOrganizerTypeID")
    val eventOrganizerTypeID: Int? = null,

    @SerializedName("aboutOrganizer")
    val aboutOrganizer: String? = null,

    @SerializedName("organizerAddress")
    val organizerAddress: String? = null,

    @SerializedName("profileImage")
    val profileImage: String? = null,

    @SerializedName("lstteammember")
    val teamMembers: List<TeamMember>? = null
)

data class TeamMember(
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
