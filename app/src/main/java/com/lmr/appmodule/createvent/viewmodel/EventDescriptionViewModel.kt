package com.lmr.appmodule.createvent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.EventDescriptionRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
import com.lmr.appmodule.createvent.model.description.EventDescriptionPost
import com.lmr.appmodule.createvent.model.description.EventDescriptionResponse
import com.lmr.appmodule.model.request.PostBasicDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDescriptionViewModel @Inject constructor(
    private val userRepo: EventDescriptionRepository
) : BaseViewModel() {

    var _postDescriptionResponse: MutableLiveData<NetworkErrorResult<EventDescriptionResponse>> = MutableLiveData()

    // Create Event Model Response
    val postDescriptionResponse: LiveData<NetworkErrorResult<EventDescriptionResponse>>
        get() = _postDescriptionResponse


    fun callPostDescription(eventDescriptionPost: EventDescriptionPost) = viewModelScope.launch {
        userRepo.postDescriptionApi(eventDescriptionPost).collect { result ->
            _postDescriptionResponse.value = result
        }
    }


}