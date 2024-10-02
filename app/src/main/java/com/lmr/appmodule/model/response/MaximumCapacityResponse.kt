package com.lmr.appmodule.model.response

import com.google.gson.annotations.SerializedName

data class MaximumCapacityModel(
    val message: String,
    val success: Boolean,
    val data: MutableList<CapacityData>
)

data class CapacityData(
    @SerializedName("totalMaximumCapacityID")
    val totalMaximumCapacityId: Int,
    @SerializedName("maximumCapacity")
    val maximumCapacity: String
)