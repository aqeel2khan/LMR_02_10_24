
package com.lmr.appmodule.repository
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.network.ApiService
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.organizerdetail.OrganizerTypeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class OrganizerDetailsRepository @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {
    suspend fun addEventOrganizerApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.addEventOrganizerApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getEventorganizerTypeApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.getEventorganizerTypeApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchAllDetails(): Flow<NetworkErrorResult<OrganizerTypeResponse>> = flow {
        emit(NetworkErrorResult.Loading())

        try {
            // API Call 1: Category Event
            val categoryResponse = apiService.OrganizerTypeAPi()
            if (!categoryResponse.success) {
                throw Exception("Category Event API failed")
            }





            emit(NetworkErrorResult.Success(categoryResponse))
        } catch (e: Exception) {
            emit(NetworkErrorResult.Error(e.message ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)

}