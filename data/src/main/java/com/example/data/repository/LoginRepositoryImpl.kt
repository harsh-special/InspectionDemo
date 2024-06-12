package com.example.data.repository

import com.example.core.Response
import com.example.core.mapper.IdentityMapper
import com.example.core.safeApiCall
import com.example.data.datasource.login.LoginDataSource
import com.example.domain.repository.LoginRepository
import com.example.network.coroutine.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Api call to get userinfo
class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val dispatcher: Dispatcher,
) : LoginRepository {

    private val tag = javaClass.name

    override suspend fun signIn(email: String, password: String): Response<Unit> =
        withContext(dispatcher.io) {
            safeApiCall(tag, IdentityMapper()) {
                loginDataSource.signIn(email, password)
            }
        }

    override suspend fun register(email: String, password: String): Response<Unit> =
        withContext(dispatcher.io) {
            safeApiCall(tag, IdentityMapper()) {
                loginDataSource.register(email, password)
            }
        }
}

