package com.lmr.appmodule.home.model


data class DashboardEventResponse(
    val message: String,
    val success: Boolean,
    val data: DashboardData
)

data class DashboardData(
    val eventDashboardData: List<EventDashboardCategory>
)

data class EventDashboardCategory(
    val dataCategory: String,
    val dataCategoryTitle: String,
    val listdata: List<EventData>?,
    val displayType: String
)

data class EventData(
    val eventName: String? = null,
    val images: String? = null,
    val eventStartDate: String? = null,
    val profileImage: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val isLiked: Boolean? = null,
    val numberOfPeopleAttending: Int? = null,
    val eventID: Int? = null,
    val eventOrganizerID: Int? = null,
    val userID: Int? = null,
    val eventOrganizerName: String? = null,
    val eventOrganizerTypeID: Int? = null,
    val aboutOrganizer: String? = null,
    val organizerAddress: String? = null
)

