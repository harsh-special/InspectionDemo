package com.example.domain.usecase

import com.example.core.Response
import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Response<Unit> =
        repository.signIn(email, password)
}