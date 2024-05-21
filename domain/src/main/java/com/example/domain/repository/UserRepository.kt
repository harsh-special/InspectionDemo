package com.example.domain.repository

import com.example.core.Response
import com.example.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Response<List<User>>
}