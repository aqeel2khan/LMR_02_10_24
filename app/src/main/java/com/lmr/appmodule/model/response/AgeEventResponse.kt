package com.lmr.appmodule.model.response

import com.google.gson.annotations.SerializedName

data class AgeGroupResponse(
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: MutableList<AgeGroupData>
)

data class AgeGroupData(
    @SerializedName("ageGroupID") val ageGroupID: Int,
    @SerializedName("age") val age: String
)
