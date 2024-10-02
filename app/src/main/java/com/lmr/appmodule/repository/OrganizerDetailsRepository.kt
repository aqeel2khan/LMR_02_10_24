
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
}