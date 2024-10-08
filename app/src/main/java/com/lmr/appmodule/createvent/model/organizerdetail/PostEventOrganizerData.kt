package com.lmr.appmodule.createvent.model.organizerdetail

import com.google.gson.annotations.SerializedName

data class PostEventOrganizerData(
    @SerializedName("eventOrganizerID") val eventOrganizerID: Int,
    @SerializedName("eventID") val eventID: Int,
    @SerializedName("eventOrganizerName") val eventOrganizerName: String,
    @SerializedName("eventOrganizerTypeID") val eventOrganizerTypeID: Int,
    @SerializedName("aboutOrganizer") val aboutOrganizer: String,
    @SerializedName("organizerAddress") val organizerAddress: String,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("lstteammember") val lstteammember: List<TeamMemberOrganizer>
)


data class TeamMemberOrganizer(
    @SerializedName("teamMemberID") val teamMemberID: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("MobileNumber") val mobileNumber: String
)