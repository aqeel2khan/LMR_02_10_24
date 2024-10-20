package com.lmr.appmodule.createvent.viewmodel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.repository.OrganizerDetailsRepository
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.createvent.model.organizerdetail.OrganizerTypeResponse
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
@HiltViewModel
class OrganizerDetailsViewModel @Inject constructor(
    private val userRepo: OrganizerDetailsRepository
): BaseViewModel() {

    // Login Api
    var _organizerTypeResponse: MutableLiveData<NetworkErrorResult<OrganizerTypeResponse>> = MutableLiveData()

    // Create Event Model Response
    val organizerTypeResponse: LiveData<NetworkErrorResult<OrganizerTypeResponse>>
        get() = _organizerTypeResponse

    // Login Api
    var _organizerpostResponse: MutableLiveData<NetworkErrorResult<EventDescriptionResponse>> = MutableLiveData()

    // Create Event Model Response
    val organizerpostResponse: LiveData<NetworkErrorResult<EventDescriptionResponse>>
        get() = _organizerpostResponse



    fun fetchOrganizerType() = viewModelScope.launch {
        userRepo.fetchAllDetails().collect { result ->
            _organizerTypeResponse.value = result
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun callPostOrganizerAPI(eventDescriptionPost: PostEventOrganizerData, fileImage: File?) = viewModelScope.launch {
        userRepo.postOrganizerApi(eventDescriptionPost,fileImage).collect { result ->
            _organizerpostResponse.value = result
        }
    }



}