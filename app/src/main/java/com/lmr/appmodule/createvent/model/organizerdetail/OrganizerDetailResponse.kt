package com.lmr.appmodule.createvent.model.organizerdetail

import com.google.gson.annotations.SerializedName


// OrganizerDetailResponse.kt
data class OrganizerTypeResponse(
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: MutableList<EventOrganizerTypeData?>
)

data class EventOrganizerTypeData(
    @SerializedName("eventOrganizerTypeID") val eventOrganizerTypeID: Int,
    @SerializedName("eventOrganizerTypeNameEnglish") val eventOrganizerTypeNameEnglish: String,
    @SerializedName("eventOrganizerTypeNameArabic") val eventOrganizerTypeNameArabic: String,
    @SerializedName("language") val language: String
)