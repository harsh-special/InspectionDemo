package com.example.data.datasource.user

import com.example.network.dto.UserDto
import com.example.network.service.ApiService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : UserDataSource {
    override suspend fun getUsers(): List<UserDto> {
        return apiService.getUsers()
    }
}