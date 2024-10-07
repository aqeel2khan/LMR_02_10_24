package com.lmr.appmodule.createvent.model.adddatetime

data class DateTimeEventPost(
    val eventDateTimeID: Int,
    val eventID: Int,
    val eventOccurType: String,
    val displayStartTime: Boolean,
    val displayEndTime: Boolean,
    val lstDateTimeDetailsRequest: List<DateTimeDetailsRequest>
)

data class DateTimeDetailsRequest(
    val eventDateAndTimeDetailsID: Int,
    val eventStartDate: String,
    val startTime: String,
    val eventEndDate: String,
    val endTime: String
)
