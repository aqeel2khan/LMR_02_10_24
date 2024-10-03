package com.lmr.appmodule.createvent.model.description

data class EventDescriptionResponse(
    val message: String,
    val success: Boolean,
    val data: DescriptionData
)

data class DescriptionData(
    val eventID: Int
)