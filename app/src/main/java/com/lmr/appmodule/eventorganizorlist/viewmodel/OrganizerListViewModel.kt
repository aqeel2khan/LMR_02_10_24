package com.lmr.appmodule.eventorganizorlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.lmr.app_utils.NetworkErrorResult
import com.lmr.appmodule.createvent.viewmodel.BaseViewModel
import com.lmr.appmodule.eventorganizorlist.repository.OrganizerListRepository
import com.lmr.appmodule.model.LoginResponseModel
import com.lmr.appmodule.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OrganizerListViewModel @Inject constructor(
    private val userRepo: OrganizerListRepository
) : BaseViewModel() {


    fun callPostEvent(jsonObject: JsonObject) = viewModelScope.launch {
       // userRepo.loginApi(jsonObject)
    }
}