package com.lmr.app_utils

sealed class NetworkErrorResult<T>(var data: T? = null, val message: String? = null) {

    class Success<T>(data: T? = null): NetworkErrorResult<T>(data=data)
    class Error<T>(message: String? =null) : NetworkErrorResult<T>(message=message)
      class Loading<T>() : NetworkErrorResult<T>()

}