package com.example.userfeature.presenter.user.state

import com.example.domain.model.User

sealed class UserUIState {
    data class Loading(val isLoading: Boolean) : UserUIState()
    data class ShowContent(val users: List<User>) : UserUIState()
    data class Error(val errorMessage: String?) : UserUIState()
}