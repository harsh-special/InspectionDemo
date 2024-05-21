package com.example.domain.usecase.userinfo

import com.example.core.Response
import com.example.domain.model.UserInfo
import com.example.domain.repository.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: UserInfoRepository
) {
    suspend operator fun invoke(userId: String?): Response<UserInfo> =
        repository.getUserInfo(userId)
}