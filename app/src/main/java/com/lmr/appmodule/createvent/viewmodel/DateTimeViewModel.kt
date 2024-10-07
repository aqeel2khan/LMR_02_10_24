
package com.lmr.appmodule.createvent.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.DateTimeRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.adddatetime.AddDateTimeApiResponse
import com.lmr.appmodule.createvent.model.adddatetime.DateTimeEventPost
import com.lmr.appmodule.createvent.model.description.EventDescriptionPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DateTimeViewModel @Inject constructor(
    private val userRepo: DateTimeRepository
): BaseViewModel() {

    var _addDateTimeResponse: MutableLiveData<NetworkErrorResult<AddDateTimeApiResponse>> = MutableLiveData()

    // Create Event Model Response
    val addDateTimeResponse: LiveData<NetworkErrorResult<AddDateTimeApiResponse>>
        get() = _addDateTimeResponse



    fun callPostDateTimeEvent(eventDescriptionPost: DateTimeEventPost) = viewModelScope.launch {
        userRepo.addDateAndTimeEventsApi(eventDescriptionPost).collect { result ->
            _addDateTimeResponse.value = result
        }
    }
}