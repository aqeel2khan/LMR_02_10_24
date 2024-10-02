package com.lmr.appmodule.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @field:SerializedName("mobileNumber")
    val mobileNumber: String? = null,

    @field:SerializedName("emailID")
    val emailID: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("image")
    val image: String? = null
)