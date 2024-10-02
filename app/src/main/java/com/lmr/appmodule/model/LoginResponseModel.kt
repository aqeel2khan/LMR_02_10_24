package com.lmr.appmodule.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(

    @field:SerializedName("data")
	val data: DataL? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("error")
	val error: ErrorL? = null,

    @field:SerializedName("status")
	val status: Int? = null
)

data class DataL(
	@field:SerializedName("company_id")
	val company_id: Int,

	@field:SerializedName("employer_id")
	val employer_id: Int,

	@field:SerializedName("industry_id")
	val industry_id: Int,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("employer_name")
	val employer_name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("company_name")
	val company_name: String? = null,

	@field:SerializedName("profile_pic")
	val profile_pic: String? = null
)

data class ErrorL(
	val any: Any? = null
)