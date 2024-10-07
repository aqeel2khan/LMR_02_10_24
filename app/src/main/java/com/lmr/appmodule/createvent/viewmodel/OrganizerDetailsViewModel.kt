package com.lmr.appmodule.createvent.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.OrganizerDetailsRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.organizerdetail.OrganizerTypeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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



    fun fetchOrganizerType() = viewModelScope.launch {
        userRepo.fetchAllDetails().collect { result ->
            _organizerTypeResponse.value = result
        }
    }


}