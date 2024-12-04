package com.lmr.appmodule.createvent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.UserRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.home.model.DashboardEventResponse
import com.lmr.appmodule.home.model.EventByOrganizerResponse
import com.lmr.appmodule.home.model.EventOrganizerProfileResponse
import com.lmr.appmodule.home.model.EventOrganizerResponse
import com.lmr.appmodule.home.model.LocationResponse
import com.lmr.appmodule.model.response.PostEventResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(
    private val userRepo: UserRepository
): BaseViewModel() {
    // Login Api
    var _loginResponse: MutableLiveData<NetworkErrorResult<LoginResponseModel>> = MutableLiveData()
    var loginResponse: LiveData<NetworkErrorResult<LoginResponseModel>> = _loginResponse
        get() = _loginResponse

    fun loginApiCall(jsonObject: JsonObject) = viewModelScope.launch {
        userRepo.loginApi(jsonObject).collect { values ->
            _loginResponse.value = values

        }
    }

    // Location Api
    var _locationResponse: MutableLiveData<NetworkErrorResult<LocationResponse>> = MutableLiveData()
    var locationResponse: LiveData<NetworkErrorResult<LocationResponse>> = _locationResponse
        get() = _locationResponse

    fun locationApiCall() = viewModelScope.launch {
        userRepo.locationApi().collect { values ->
            _locationResponse.value = values

        }
    }

    // Event Organizer List Api
    var _eventOrganizerResponse: MutableLiveData<NetworkErrorResult<EventOrganizerResponse>> = MutableLiveData()
    var eventOrganizerResponse: LiveData<NetworkErrorResult<EventOrganizerResponse>> = _eventOrganizerResponse
        get() = _eventOrganizerResponse

    fun eventOrganizerApiCall() = viewModelScope.launch {
        userRepo.eventOrganizerListApi().collect { values ->
            _eventOrganizerResponse.value = values

        }
    }

    // Event Organizer List Api
    var _dashboardEventResponse: MutableLiveData<NetworkErrorResult<DashboardEventResponse>> = MutableLiveData()
    var dashboardEventResponse: LiveData<NetworkErrorResult<DashboardEventResponse>> = _dashboardEventResponse
        get() = _dashboardEventResponse

    fun dashboardEventApiCall(locationId:String) = viewModelScope.launch {
        userRepo.dashboardEventListApi(locationId).collect { values ->
            _dashboardEventResponse.value = values

        }
    }

    // Event By Organizer  List Api
    var _eventByOrganizerResponse: MutableLiveData<NetworkErrorResult<EventByOrganizerResponse>> = MutableLiveData()
    var eventByOrganizerResponse: LiveData<NetworkErrorResult<EventByOrganizerResponse>> = _eventByOrganizerResponse
        get() = _eventByOrganizerResponse

    fun eventByOrganizerApiCall(jsonObject: JsonObject) = viewModelScope.launch {
        userRepo.eventByOrganizerApi(jsonObject).collect { values ->
            _eventByOrganizerResponse.value = values

        }
    }

    // Event  Organizer Profile List Api
    var _eventOrganizerProfileResponse: MutableLiveData<NetworkErrorResult<EventOrganizerProfileResponse>> = MutableLiveData()
    var eventOrganizerProfilerResponse: LiveData<NetworkErrorResult<EventOrganizerProfileResponse>> = _eventOrganizerProfileResponse
        get() = _eventOrganizerProfileResponse

    fun eventOrganizerProfileApiCall(organizerId:String) = viewModelScope.launch {
        userRepo.eventOrganizeProfilerApi(organizerId).collect { values ->
            _eventOrganizerProfileResponse.value = values

        }
    }

    // Post Event Api
    var _postEventResponse: MutableLiveData<NetworkErrorResult<PostEventResponse>> = MutableLiveData()
    var postEventResponse: LiveData<NetworkErrorResult<PostEventResponse>> = _postEventResponse
        get() = _postEventResponse

    fun postEventApiCall(jsonObject: JsonObject) = viewModelScope.launch {
        userRepo.postEventApi(jsonObject).collect { values ->
            _postEventResponse.value = values

        }
    }

}