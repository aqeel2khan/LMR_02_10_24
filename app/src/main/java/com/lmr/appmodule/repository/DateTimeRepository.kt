package com.lmr.appmodule.repository

import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.network.ApiService
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.adddatetime.AddDateTimeApiResponse
import com.lmr.appmodule.createvent.model.adddatetime.DateTimeEventPost
import com.lmr.appmodule.createvent.model.description.EventDescriptionPost
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class DateTimeRepository @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {
    suspend fun addDateAndTimeApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.addDateAndTimeApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addDateAndTimeDetailsApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.addDateAndTimeDetailsApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addDateAndTimeEventsApi(mPostBasicDetailEvent: DateTimeEventPost): Flow<NetworkErrorResult<AddDateTimeApiResponse>> =
        flow {
            emit(NetworkErrorResult.Loading())
            try {
                val response = apiService.postAddDateTimeEvent(mPostBasicDetailEvent)
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