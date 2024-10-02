package com.lmr.network.errorhandling

interface ErrorHandler {
    fun getErrorMessageFrom(ex: Exception): String?
}