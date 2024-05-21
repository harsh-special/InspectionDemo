package com.example.network.service

import com.example.network.dto.UserDto
import com.example.network.dto.UserInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{id}")
    suspend fun getUserInfo(@Path("id") userId: String?): UserInfoDto
}