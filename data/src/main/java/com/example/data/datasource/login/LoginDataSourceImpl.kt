package com.example.data.datasource.login

import com.example.network.service.ApiService
import com.example.network.model.LoginRequest
import com.example.network.model.RegisterRequest
import retrofit2.Response
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : LoginDataSource {

    override suspend fun signIn(email : String, password : String): Response<Unit> {
        return apiService.signIn(LoginRequest(email , password ))
    }

    override suspend fun register(email : String, password : String): Response<Unit> {
        return apiService.register(RegisterRequest(email,password))
    }
}