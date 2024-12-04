package com.lmr.appmodule.home.model

data class EventOrganizerResponse(
    val message: String,
    val success: Boolean,
    val data: List<Organizer>
)

data class Organizer(
    val eventOrganizerID: Int,
    val eventID: Int,
    val eventOrganizerName: String,
    val eventOrganizerTypeID: Int,
    val aboutOrganizer: String,
    val organizerAddress: String,
    val profileImage: String,
    val userID: Int,
    val eventCount: Int
)

