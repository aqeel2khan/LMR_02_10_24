// BasicDetailViewModel.kt
package com.lmr.appmodule.createvent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.request.PostBasicDetailEvent
import com.lmr.appmodule.model.response.*
import com.lmr.appmodule.repository.BasicDetailRepository
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.repository.AllDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicDetailViewModel @Inject constructor(
    private val basicDetailRepository: BasicDetailRepository
) : BaseViewModel() {

    // LiveData for all details
    private val _allDetailsResponse: MutableLiveData<NetworkErrorResult<AllDetailsResponse>> = MutableLiveData()
    val allDetailsResponse: LiveData<NetworkErrorResult<AllDetailsResponse>> get() = _allDetailsResponse

    // LiveData for post event
    private val _postEventResponse: MutableLiveData<NetworkErrorResult<PostEventResponse>> = MutableLiveData()
    val postEventResponse: LiveData<NetworkErrorResult<PostEventResponse>> get() = _postEventResponse

    /**
     * Fetches all details sequentially.
     */
    fun fetchAllDetails() = viewModelScope.launch {
        basicDetailRepository.fetchAllDetails().collect { result ->
            _allDetailsResponse.value = result
        }
    }

    /**
     * Posts event data.
     */
    fun postEventData(mPostBasicDetailEvent: PostBasicDetailEvent) = viewModelScope.launch {
        basicDetailRepository.postEventData(mPostBasicDetailEvent).collect { result ->
            _postEventResponse.value = result
        }
    }
}
