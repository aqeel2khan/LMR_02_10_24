
package com.lmr.appmodule.repository
import android.os.Build
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.network.ApiService
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.createvent.model.organizerdetail.OrganizerTypeResponse
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.Base64

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


    @RequiresApi(Build.VERSION_CODES.O)
    fun imageToBase64(imagePath: String): String {
        val file = File(imagePath)
        val bytes = file.readBytes()
        return Base64.getEncoder().encodeToString(bytes)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun postOrganizerApi(mPostBasicDetailEvent: PostEventOrganizerData, fileImage: File?): Flow<NetworkErrorResult<EventDescriptionResponse>> =
        flow {
            emit(NetworkErrorResult.Loading())
            try {

             var imgBase64=   imageToBase64(mPostBasicDetailEvent.profileImage)
                mPostBasicDetailEvent.profileImage = imgBase64

                val response:EventDescriptionResponse =    apiService.postOrganizer(mPostBasicDetailEvent)

                if (response.success) {
                    emit(NetworkErrorResult.Success(response))
                } else {
                    emit(NetworkErrorResult.Error("Post Event API failed"))
                }
            } catch (e: Exception) {
                emit(NetworkErrorResult.Error(e.message ?: "An unexpected error occurred"))
            }
        }.flowOn(Dispatchers.IO)


    fun getMimeType(file: File): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

}