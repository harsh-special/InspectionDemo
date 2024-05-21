package com.example.domain.repository

import com.example.core.Response
import com.example.domain.model.UserInfo

interface UserInfoRepository {
    suspend fun getUserInfo(userId: String?): Response<UserInfo>
}