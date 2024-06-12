package com.example.userfeature.presenter.user.state

sealed class UserUIState {
    data class Loading(val isLoading: Boolean) : UserUIState()
    data object NavigateToAfterLogin : UserUIState()
    data object ShowUserAlreadyExistError : UserUIState()

    data object ShowUserError : UserUIState()
}