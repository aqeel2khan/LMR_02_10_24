package com.lmr.appmodule.createvent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.createvent.model.eventbookdateseat.EventBookingRequest
import com.lmr.appmodule.createvent.model.organizerdetail.PostEventOrganizerData
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.EventBookDateSeatRepository
import com.lmr.appmodule.repository.TicketingSeatDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class EventDateSeatViewModel @Inject constructor(
    private val userRepo: EventBookDateSeatRepository
): BaseViewModel() {

    // Login Api
    var _eventDescriptionResponse: MutableLiveData<NetworkErrorResult<EventDescriptionResponse>> = MutableLiveData()

    // Create Event Model Response
    val eventDescriptionResponse: LiveData<NetworkErrorResult<EventDescriptionResponse>>
        get() = _eventDescriptionResponse



    //callPostOrganizerAPI

    fun callEventbookDateSeatAPI(eventDescriptionPost: EventBookingRequest) = viewModelScope.launch {
        userRepo.addEventBookDateSeat(eventDescriptionPost).collect { result ->
            _eventDescriptionResponse.value = result
        }
    }


}