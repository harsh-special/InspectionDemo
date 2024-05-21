package com.example.userfeature.presenter.userinfo.state

import com.example.domain.model.UserInfo

sealed class UserInfoUIState {
    data class Loading(val isLoading: Boolean) : UserInfoUIState()
    data class ShowContent(val userInfo: UserInfo?) : UserInfoUIState()
    data class Error(val errorMessage: String?) : UserInfoUIState()
}