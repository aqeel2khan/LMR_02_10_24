package com.lmr.network


import com.google.gson.JsonObject
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.model.request.PostBasicDetailEvent
import com.lmr.appmodule.model.response.AgeGroupResponse
import com.lmr.appmodule.createvent.model.EventCategoryModelResponse
import com.lmr.appmodule.createvent.model.description.EventDescriptionPost
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.model.response.EventResponse
import com.lmr.appmodule.model.response.MaximumCapacityModel
import com.lmr.appmodule.model.response.PostEventResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/User")
    suspend fun userLogin(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("api/User/VerifyOTP")
    suspend fun verifyOTPApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("api/Events/AddDescription")
    fun postEventDescription(@Body eventDescriptionPost: EventDescriptionPost): EventDescriptionResponse

    @GET("api/Events/GetEventCategory")
    suspend fun categoryEventApi(): EventCategoryModelResponse

    @GET("api/Events/GetEventType")
    suspend fun EventTypeApi(): EventResponse

    @GET("api/Events/GetTotalMaximumCapacity")
    suspend fun maximumCapacityApi(): MaximumCapacityModel

    @GET("api/Events/GetAgeGroup")
    suspend fun ageApi(): AgeGroupResponse

    @POST("api/Events")
    suspend fun postEvent(@Body mPostData: PostBasicDetailEvent): PostEventResponse

    @GET("/api/Events/GetTotalMaximumCapacity")
    suspend fun getTotalMaximumCapacityApi(@Body jsonObject: JsonObject): LoginResponseModel

    @GET("/api/Events/GetEventorganizerType")
    suspend fun getEventorganizerTypeApi(@Body jsonObject: JsonObject): LoginResponseModel

    @GET("/api/Events/GetEventType")
    suspend fun getEventTypeApi(@Body jsonObject: JsonObject): LoginResponseModel

    @GET("/api/Events/GetEventCategory")
    suspend fun getEventCategoryApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/EventPost")
    suspend fun eventPostApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddTicketingDetails")
    suspend fun addTicketingDetailsApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddTicketDetails")
    suspend fun addTicketDetailsApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddTeamMeber")
    suspend fun addTeamMeberApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddEventOrganizer")
    suspend fun addEventOrganizerApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddLocation")
    suspend fun addLocationApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddDateAndTimeDetails")
    suspend fun addDateAndTimeDetailsApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddDateAndTime")
    suspend fun addDateAndTimeApi(@Body jsonObject: JsonObject): LoginResponseModel

    @POST("/api/Events/AddDescription")
    suspend fun addDescriptionApi(@Body jsonObject: JsonObject): LoginResponseModel


}