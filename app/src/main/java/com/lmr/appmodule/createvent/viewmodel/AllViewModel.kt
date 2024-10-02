package com.lmr.appmodule.createvent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.UserRepository
import com.lmr.app_utils.NetworkErrorResult
import com.google.gson.JsonObject
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

}