package com.example.domain.usecase.user

import com.example.core.Response
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Response<List<User>> = repository.getUsers()
}