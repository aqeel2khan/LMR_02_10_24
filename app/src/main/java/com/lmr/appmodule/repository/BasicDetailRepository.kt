// BasicDetailRepository.kt
package com.lmr.appmodule.repository

import com.lmr.appmodule.model.request.PostBasicDetailEvent
import com.lmr.appmodule.model.response.*
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.createvent.model.EventCategoryModelResponse
import com.lmr.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BasicDetailRepository @Inject constructor(private val apiService: ApiService):
    BaseApiResponse() {

    /**
     * Fetches all required data sequentially.
     * If any API call fails, the process is halted, and an error is returned.
     */
    suspend fun fetchAllDetails(): Flow<NetworkErrorResult<AllDetailsResponse>> = flow {
        emit(NetworkErrorResult.Loading())

        try {
            // API Call 1: Category Event
            val categoryResponse = apiService.categoryEventApi()
            if (!categoryResponse.success) {
                throw Exception("Category Event API failed")
            }

            // API Call 2: Event Type
            val eventTypeResponse = apiService.EventTypeApi()
            if (!eventTypeResponse.success) {
                throw Exception("Event Type API failed")
            }

            // API Call 3: Maximum Capacity
            val maxCapacityResponse = apiService.maximumCapacityApi()
            if (!maxCapacityResponse.success) {
                throw Exception("Maximum Capacity API failed")
            }

            // API Call 4: Age Group
            val ageGroupResponse = apiService.ageApi()
            if (!ageGroupResponse.success) {
                throw Exception("Age Group API failed")
            }

            // Combine all responses
            val allDetails = AllDetailsResponse(
                categoryEvent = categoryResponse,
                eventType = eventTypeResponse,
                maximumCapacity = maxCapacityResponse,
                ageGroup = ageGroupResponse
            )

            emit(NetworkErrorResult.Success(allDetails))
        } catch (e: Exception) {
            emit(NetworkErrorResult.Error(e.message ?: "An unexpected error occurred"))
        }
    }.flowOn(Dispatchers.IO)

    /**
     * Posts event data.
     */
    suspend fun postEventData(mPostBasicDetailEvent: PostBasicDetailEvent): Flow<NetworkErrorResult<PostEventResponse>> = flow {
        emit(NetworkErrorResult.Loading())
        try {
            val response = apiService.postEvent(mPostBasicDetailEvent)
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

/**
 * A data class to hold all details responses.
 */
data class AllDetailsResponse(
    val categoryEvent: EventCategoryModelResponse,
    val eventType: EventResponse,
    val maximumCapacity: MaximumCapacityModel,
    val ageGroup: AgeGroupResponse
)
