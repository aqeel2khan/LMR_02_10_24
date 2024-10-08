package com.lmr.appmodule.createvent.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.TicketingSeatDetailsRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TicketingSeatDetailsViewModel @Inject constructor(
    private val userRepo: TicketingSeatDetailsRepository
): BaseViewModel() {

    // Login Api
    var _loginResponse: MutableLiveData<NetworkErrorResult<LoginResponseModel>> = MutableLiveData()

    // Create Event Model Response
    val loginResponse: LiveData<NetworkErrorResult<LoginResponseModel>>
        get() = _loginResponse

    fun loginApiCall(jsonObject: JsonObject) = viewModelScope.launch {
        userRepo.addTicketDetailsApi(jsonObject).collect { values ->
            _loginResponse.value = values
        }
    }
    fun callPostEvent(jsonObject: JsonObject) = viewModelScope.launch {
        userRepo.addTicketDetailsApi(jsonObject)
    }

    //callPostOrganizerAPI

    fun callPostOrganizerAPI(eventDescriptionPost: PostEventOrganizerData) = viewModelScope.launch {
        userRepo.addTicketingDetailsApi(eventDescriptionPost).collect { result ->
          //  _organizerpostResponse.value = result
        }
    }


}