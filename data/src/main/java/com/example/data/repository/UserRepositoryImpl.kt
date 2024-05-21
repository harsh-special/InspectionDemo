package com.example.data.repository

import com.example.core.Response
import com.example.core.safeApiCall
import com.example.data.datasource.user.UserDataSource
import com.example.data.mapper.UserMapper
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.network.coroutine.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Api call to get userinfo
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val dispatcher: Dispatcher,
    private val userMapper: UserMapper,
) : UserRepository {

    private val tag = javaClass.name
    override suspend fun getUsers(): Response<List<User>> = withContext(dispatcher.io) {
        safeApiCall(tag) {
            userMapper.map(userDataSource.getUsers())
        }
    }
}

