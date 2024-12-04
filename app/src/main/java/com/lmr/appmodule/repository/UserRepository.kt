package com.lmr.appmodule.repository

import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.network.ApiService
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.home.model.DashboardEventResponse
import com.lmr.appmodule.home.model.EventByOrganizerResponse
import com.lmr.appmodule.home.model.EventOrganizerProfileResponse
import com.lmr.appmodule.home.model.EventOrganizerResponse
import com.lmr.appmodule.home.model.LocationResponse
import com.lmr.appmodule.model.response.PostEventResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {
    suspend fun loginApi(jsonObject: JsonObject): Flow<NetworkErrorResult<LoginResponseModel>> {
        return flow {
            emit(safeApiCall { apiService.verifyOTPApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun locationApi(): Flow<NetworkErrorResult<LocationResponse>> {
        return flow {
            emit(safeApiCall { apiService.getLocation() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun eventOrganizerListApi(): Flow<NetworkErrorResult<EventOrganizerResponse>> {
        return flow {
            emit(safeApiCall { apiService.getEventOrganizerList() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun dashboardEventListApi(locationId:String): Flow<NetworkErrorResult<DashboardEventResponse>> {
        return flow {
            emit(safeApiCall { apiService.getEventDashboardList(locationId) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun eventByOrganizerApi(jsonObject: JsonObject): Flow<NetworkErrorResult<EventByOrganizerResponse>> {
        return flow {
            emit(safeApiCall { apiService.getEventByOrganizerList(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun eventOrganizeProfilerApi(organizerId:String): Flow<NetworkErrorResult<EventOrganizerProfileResponse>> {
        return flow {
            emit(safeApiCall { apiService.getEventOrganizerProfileList(organizerId) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postEventApi(jsonObject: JsonObject): Flow<NetworkErrorResult<PostEventResponse>> {
        return flow {
            emit(safeApiCall { apiService.eventPostApi(jsonObject) })
        }.flowOn(Dispatchers.IO)
    }
}