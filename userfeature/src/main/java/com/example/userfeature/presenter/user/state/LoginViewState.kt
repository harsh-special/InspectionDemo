package com.example.userfeature.presenter.user.state

data class LoginViewState(
    val username : String,
    val password : String,
    val usernameError: Boolean,
    val passwordError: Boolean,
)
