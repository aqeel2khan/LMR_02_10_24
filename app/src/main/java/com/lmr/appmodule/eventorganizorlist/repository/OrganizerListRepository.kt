package com.lmr.appmodule.eventorganizorlist.repository

import com.google.gson.JsonObject
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.BaseApiResponse
import com.lmr.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OrganizerListRepository @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {
    suspend fun loginApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.verifyOTPApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }
}