package com.example.data.datasource.login


import retrofit2.Response

interface LoginDataSource {
    suspend fun signIn(email: String, password: String): Response<Unit>

    suspend fun register(email: String, password: String): Response<Unit>
}