package com.example.data.datasource.userinfo

import com.example.network.dto.UserInfoDto
import com.example.network.service.ApiService
import javax.inject.Inject

class UserInfoDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : UserInfoDataSource {
    override suspend fun getUserInfo(userId: String?): UserInfoDto {
        return apiService.getUserInfo(userId)
    }
}