package com.lmr.appmodule.home.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("data") val locationDetails: List<LocationDetails>?,
    @SerializedName("message") val message: String?,
    @SerializedName("success") val success: Boolean?
) {
    data class LocationDetails(
        @SerializedName("latitude") val latitude: String?,
        @SerializedName("locationID") val locationID: Long?,
        @SerializedName("locationName") val locationName: String?,
        @SerializedName("longitude") val longitude: String?
    )
}
