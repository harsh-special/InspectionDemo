package com.example.data.datasource.user

import com.example.network.dto.UserDto

interface UserDataSource {
    suspend fun getUsers(): List<UserDto>
}