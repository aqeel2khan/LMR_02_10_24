package com.lmr.appmodule.createvent.model.adddatetime

data class AddDateTimeApiResponse(
    val message: String,
    val success: Boolean,
    val data: DatetimeEventData
)

data class DatetimeEventData(
    val eventID: Int
)