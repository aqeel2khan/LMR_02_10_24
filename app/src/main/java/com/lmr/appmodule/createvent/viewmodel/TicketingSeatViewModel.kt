package com.lmr.appmodule.createvent.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.TicketingSeatRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.createvent.model.eventbookdateseat.EventBookingRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TicketingSeatViewModel @Inject constructor(
    private val userRepo: TicketingSeatRepository
): BaseViewModel() {

    var _eventDescriptionResponse: MutableLiveData<NetworkErrorResult<EventDescriptionResponse>> = MutableLiveData()

    // Create Event Model Response
    val eventDescriptionResponse: LiveData<NetworkErrorResult<EventDescriptionResponse>>
        get() = _eventDescriptionResponse

    fun callEventbookDateSeatAPI(eventDescriptionPost: EventBookingRequest) = viewModelScope.launch {
        userRepo.addEventBookDateSeat(eventDescriptionPost).collect { result ->
            _eventDescriptionResponse.value = result
        }
    }
}