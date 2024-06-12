package com.example.domain.repository


import com.example.core.Response

interface LoginRepository {
    suspend fun signIn(email : String, password : String): Response<Unit>

    suspend fun register(email : String, password : String): Response<Unit>
}