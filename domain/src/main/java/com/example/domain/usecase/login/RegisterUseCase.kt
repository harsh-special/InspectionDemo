package com.example.domain

import com.example.core.Response
import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Response<Unit> =
        repository.register(email, password)
}