package com.example.domain.model

class UserInfo(
    val name: String,
    val email: String,
    val username: String,
    val address: Address
) {

    data class Address(
        val zipcode: String,
        val city: String,
        val street: String
    )
}