package com.example.network.service

import com.example.network.dto.InspectionDto
import com.example.network.model.LoginRequest
import com.example.network.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun signIn(
        @Body request: LoginRequest
    ): Response<Unit>


    @Headers("Content-Type: application/json")
    @POST("api/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("api/inspections/start")
    suspend fun inspectionStart(): Response<InspectionDto>

}