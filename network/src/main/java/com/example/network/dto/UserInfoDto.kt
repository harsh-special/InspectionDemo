package com.example.network.dto

data class UserInfoDto(
    val address: Address? = null,
    val phone: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val email: String? = null,
    val username: String? = null
)
