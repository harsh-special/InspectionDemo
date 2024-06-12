package com.example.userfeature.presenter.user.intent

sealed interface LoginIntent {
    data class UsernameChanged(val username: String) : LoginIntent
    data class PasswordChanged(val password: String) : LoginIntent
    data object SubmitLogin : LoginIntent
    data object RegisterUser : LoginIntent
}
