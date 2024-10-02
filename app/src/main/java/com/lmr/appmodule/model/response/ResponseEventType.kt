package com.lmr.appmodule.model.response

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @field:SerializedName("message") val message: String,
    @field:SerializedName("success") val success: Boolean,
    @field:SerializedName("data") val data: MutableList<Event>
)

data class Event(
    @field:SerializedName("eventTypeID") val eventTypeID: Int,
    @field:SerializedName("eventTypeNameEnglish") val eventTypeNameEnglish: String,
    @field:SerializedName("eventTypeNameArabic") val eventTypeNameArabic: String,
    @field:SerializedName("language") val language: String
)