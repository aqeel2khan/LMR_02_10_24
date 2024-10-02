package com.lmr.appmodule.model.request

import com.google.gson.annotations.SerializedName

data class VerifyOTPModel(
    @field:SerializedName("MobileNumber")
    val mobileNumber: String? = null,

    @field:SerializedName("OTP")
    val otp: String? = null,
)

data class EventsModel(
    @field:SerializedName("eventBasicDetailsID")
    val eventBasicDetailsID: Int? = null,

    @field:SerializedName("eventID")
    val eventID: Int? = null,

    @field:SerializedName("eventName")
    val eventName: String? = null,

    @field:SerializedName("eventTypeID")
    val eventTypeID: Int? = null,

    @field:SerializedName("eventCategoryID")
    val eventCategoryID: Int? = null,

    @field:SerializedName("numberOfPeople")
    val numberOfPeople: Int? = null,

    @field:SerializedName("maximumCapacity")
    val maximumCapacity: Int? = null,

    @field:SerializedName("publishingMethod")
    val publishingMethod: Int? = null,

    @field:SerializedName("ticketType")
    val ticketType: Int? = null,

    @field:SerializedName("ageGroupID")
    val ageGroupID: Int? = null,
)

data class EventDescriptionRequest(
    @field:SerializedName("eventDescriptionID")
    val eventDescriptionID: Int? = null,

    @field:SerializedName("eventID")
    val eventID: Int? = null,

    @field:SerializedName("eventDescriptions")
    val eventDescriptions: String? = null,

    @field:SerializedName("images")
    val images: String? = null,

    @field:SerializedName("videos")
    val videos: String? = null,

    @field:SerializedName("document")
    val document: String? = null,
)

data class EventDateTimeRequest(
    @field:SerializedName("eventDateTimeID")
    val eventDateTimeID: Int? = null,

    @field:SerializedName("eventID")
    val eventID: Int? = null,

    @field:SerializedName("eventOccurType")
    val eventOccurType: String? = null,
)

data class EventDateAndTimeDetailsRequest(
    @SerializedName("eventDateAndTimeDetailsID")
    val eventDateAndTimeDetailsID: Int? = null,

    @SerializedName("eventDateTimeID")
    val eventDateTimeID: Int? = null,

    @SerializedName("displayStartTime")
    val displayStartTime: Boolean? = null,

    @SerializedName("displayEndTime")
    val displayEndTime: Boolean? = null,

    @SerializedName("eventStartDate")
    val eventStartDate: String? = null,

    @SerializedName("startTime")
    val startTime: String? = null,

    @SerializedName("eventEndDate")
    val eventEndDate: String? = null,

    @SerializedName("endTime")
    val endTime: String? = null
)

data class EventLocationRequest(
    @SerializedName("eventLocationID")
    val eventLocationID: Int? = null,

    @SerializedName("eventID")
    val eventID: Int? = null,

    @SerializedName("eventLatitude")
    val eventLatitude: String? = null,

    @SerializedName("eventLongitude")
    val eventLongitude: String? = null
)