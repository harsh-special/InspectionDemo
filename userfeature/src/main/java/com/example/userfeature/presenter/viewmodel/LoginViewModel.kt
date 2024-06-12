package com.example.userfeature.presenter.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Response
import com.example.domain.RegisterUseCase
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.ValidateLoginFieldsUseCase
import com.example.userfeature.presenter.user.intent.LoginIntent
import com.example.userfeature.presenter.user.state.LoginViewState
import com.example.userfeature.presenter.user.state.UserUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val validateLoginFieldsUseCase: ValidateLoginFieldsUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = _viewState

    private val _signInState = MutableLiveData<UserUIState>()
    val signInState: LiveData<UserUIState> = _signInState

    init {
        _viewState.value = LoginViewState("", "", usernameError = false, passwordError = false)
    }

    fun processIntent(intent: LoginIntent) {
        val currentState = _viewState.value
        currentState?.let {
            when (intent) {
                is LoginIntent.UsernameChanged -> {
                    _viewState.value = currentState.copy(username = intent.username)
                }

                is LoginIntent.PasswordChanged -> {
                    _viewState.value = currentState.copy(password = intent.password)
                }


                is LoginIntent.SubmitLogin -> {
                    validateAndSubmit(currentState)
                }

                is LoginIntent.RegisterUser -> {
                    validateAndSubmit(currentState, true)
                }
            }
        }
    }


    private fun validateAndSubmit(state: LoginViewState, isRegister: Boolean = false) {
        val validationResult =
            validateLoginFieldsUseCase.invoke(state.username, state.password)

        if (validationResult.usernameError || validationResult.passwordError) {
            _viewState.value = state.copy(
                usernameError = validationResult.usernameError,
                passwordError = validationResult.passwordError
            )
        } else {
            _signInState.value = UserUIState.Loading(true)
            viewModelScope.launch {
                if (isRegister) {
                    handleResult(registerUseCase(state.username, state.password), true)
                } else {
                    handleResult(loginUseCase(state.username, state.password), false)
                }
                _signInState.value = UserUIState.Loading(false)
            }
        }
    }

    private fun handleResult(result: Response<Unit>, isRegister: Boolean) {
        _signInState.value =
            when (result) {
                is Response.Success -> UserUIState.NavigateToAfterLogin
                is Response.Error -> {
                    if (isRegister && result.code == 401) {
                        UserUIState.ShowUserAlreadyExistError
                    } else {
                        UserUIState.ShowUserError
                    }
                }

                is Response.Loading -> UserUIState.Loading(result.showLoading)
            }
    }
}


