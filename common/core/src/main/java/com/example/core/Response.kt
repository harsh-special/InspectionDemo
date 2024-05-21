package com.example.core

sealed interface Response<out T> {
    data class Success<out T>(val data: T) : Response<T>
    data class Error<T>(val exception: Throwable) : Response<T>
    data class Loading<T>(val showLoading: Boolean) : Response<T>
}