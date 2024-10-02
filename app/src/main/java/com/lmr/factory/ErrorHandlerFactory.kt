package com.lmr.factory

import com.lmr.di.Http
import com.lmr.di.PreLogin
import com.lmr.appmodule.model.RequestType
import com.lmr.network.errorhandling.ErrorHandler
import javax.inject.Inject

class ErrorHandlerFactory @Inject constructor(
    @Http private val httpErrorHandler: ErrorHandler,
    @PreLogin private val preLoginErrorHandler: ErrorHandler
): IFactory<RequestType, ErrorHandler> {
    override fun create(data: RequestType): ErrorHandler {
        return when(data)
        {
            RequestType.POST_LOGIN -> {
                httpErrorHandler
            }
            RequestType.PRE_LOGIN -> {
                preLoginErrorHandler
            }
        }
    }
}