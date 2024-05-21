package com.example.data.datasource.userinfo

import com.example.network.dto.UserInfoDto

interface UserInfoDataSource {
    suspend fun getUserInfo(userId: String?): UserInfoDto
}