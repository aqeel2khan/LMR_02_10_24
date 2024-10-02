package com.lmr.appmodule.model.response

import com.google.gson.annotations.SerializedName

data class PostEventResponse(
    @SerializedName("message") val message: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Int
)