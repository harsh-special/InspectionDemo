package com.example.userfeature.presenter.userinfo.intent


sealed interface UserInfoIntent {
    data object FetchUserInfo : UserInfoIntent
    sealed interface BackPressed {
        data object BackPressClicked : UserInfoIntent
    }
}
