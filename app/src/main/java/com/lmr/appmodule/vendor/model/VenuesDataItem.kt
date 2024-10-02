package com.lmr.appmodule.vendor.model

data class VenuesDataItem(
    val venueName: String?,
    val venueAddress: String?,
    val venueImage: String?,
    val venueComment: String?,
    val venueRating: Int?,
    val venueReviews: Int?,
    val areaAvailable: Int?,
    val startingPrice: String?
)