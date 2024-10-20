package com.lmr.appmodule.createvent.model.eventbookdateseat

import java.text.SimpleDateFormat
import java.util.Date

data class TicketBookingDetailRequest(
    val ticketBookingDetailsID: Int,
    val ticketName: String,
    val availableQuantity: Int,
    val price: Double,
    val minimumTicketNumber: Int,
    val maximumTicketNumber: Int,
    val ticketDescription: String,
    val visibility: String,
    val ticketFeeAbsorbtion: Boolean
)

data class EventBookingRequest(
    val seatDetailsID: Int,
    val eventID: Int,
    val eventPaidType: Int,
    val bookingStartDate: String,
    val bookingEndDate: String,
    val bookingStartTime: String,
    val bookingEndTime: String,
    val lstTicketBookingDetailRequest: List<TicketBookingDetailRequest>
)

fun parseDate(date: String, format: String = "dd/MM/yyyy"): Date {
    return SimpleDateFormat(format).parse(date)!!
}

fun parseTime(time: String, format: String = "HH:mm"): Date {
    return SimpleDateFormat(format).parse(time)!!
}
