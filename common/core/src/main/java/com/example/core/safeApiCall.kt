package com.example.core

import com.example.core.logs.LoggerDelegateProvider


suspend fun <T> safeApiCall(className: String, apiCall: suspend () -> T): Response<T> {

    val loggerDelegate by LoggerDelegateProvider(className)
    return try {
        val response = apiCall()
        loggerDelegate.info("Response is $response")
        Response.Success(response)
    } catch (exception: Exception) {
        loggerDelegate.error("Error is ${exception.localizedMessage}")
        Response.Error(exception)
    }
}