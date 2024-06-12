package com.example.core

import com.example.core.logs.LoggerDelegateProvider
import com.example.core.mapper.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response as RetrofitResponse

suspend fun <T, R> safeApiCall(
    className: String,
    mapper: Mapper<T, R>,
    apiCall: suspend () -> RetrofitResponse<T>
): Response<R> {
    val loggerDelegate by LoggerDelegateProvider(className)
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    loggerDelegate.info("Response is $it")
                    val mapperData: R = mapper.map(it)
                    Response.Success(mapperData)
                } ?: run {
                    loggerDelegate.error("Error: response body is null")
                    Response.Error(NullPointerException("Response body is null"))
                }
            } else {
                val error = retrofit2.HttpException(response)
                loggerDelegate.error("HTTP error is ${error.localizedMessage}")
                Response.Error(error, response.code())
            }
        } catch (exception: Exception) {
            loggerDelegate.error("Error is ${exception.localizedMessage}")
            Response.Error(exception)
        }
    }
}
