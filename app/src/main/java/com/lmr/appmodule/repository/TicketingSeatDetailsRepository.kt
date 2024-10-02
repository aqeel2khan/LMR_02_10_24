package com.lmr.appmodule.repository

import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.network.ApiService
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class TicketingSeatDetailsRepository @Inject constructor(private val apiService: ApiService) :
    BaseApiResponse() {
    suspend fun addTicketDetailsApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.addTicketDetailsApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addTicketingDetailsApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.addTicketingDetailsApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }
}