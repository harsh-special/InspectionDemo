package com.example.data.repository

import com.example.core.Response
import com.example.core.safeApiCall
import com.example.data.datasource.userinfo.UserInfoDataSource
import com.example.data.mapper.UserInfoMapper
import com.example.domain.model.UserInfo
import com.example.domain.repository.UserInfoRepository
import com.example.network.coroutine.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Api call to get list of users and userinfo
class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource,
    private val dispatcher: Dispatcher,
    private val userInfoMapper: UserInfoMapper
) : UserInfoRepository {

    private val tag = javaClass.name
    override suspend fun getUserInfo(userId: String?): Response<UserInfo> =
        withContext(dispatcher.io) {
            safeApiCall(tag) {
                userInfoMapper.map(
                    userInfoDataSource.getUserInfo(userId)
                )
            }
        }
}

