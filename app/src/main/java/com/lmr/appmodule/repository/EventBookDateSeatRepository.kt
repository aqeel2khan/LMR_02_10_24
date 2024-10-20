package com.lmr.appmodule.repository

import com.google.gson.JsonObject
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.createvent.model.eventbookdateseat.EventBookingRequest
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EventBookDateSeatRepository @Inject constructor(private val apiService: ApiService) :
    BaseApiResponse() {
    suspend fun addTicketDetailsApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.addTicketDetailsApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun addEventBookDateSeat(mPostBasicDetailEvent: EventBookingRequest): Flow<NetworkErrorResult<EventDescriptionResponse>> =
        flow {
            emit(NetworkErrorResult.Loading())
            try {
                val response = apiService.postEventBookDateSeat(mPostBasicDetailEvent)
                if (response.success) {
                    emit(NetworkErrorResult.Success(response))
                } else {
                    emit(NetworkErrorResult.Error("Post Event API failed"))
                }
            } catch (e: Exception) {
                emit(NetworkErrorResult.Error(e.message ?: "An unexpected error occurred"))
            }
        }.flowOn(Dispatchers.IO)

}