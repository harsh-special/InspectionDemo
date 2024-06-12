package com.example.domain.usecase

import javax.inject.Inject

class ValidateLoginFieldsUseCase @Inject constructor() {

    fun invoke(username: String?, password: String?): ValidationResult {
        val usernameError = username.isNullOrBlank() || username.contains("@").not()
        val passwordError = password.isNullOrBlank()

        return ValidationResult(usernameError, passwordError)
    }

    data class ValidationResult(val usernameError: Boolean, val passwordError: Boolean)
}
