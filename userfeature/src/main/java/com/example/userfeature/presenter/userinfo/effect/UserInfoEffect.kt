package com.example.userfeature.presenter.userinfo.effect

sealed interface UserInfoEffect {
    sealed interface BackPressEffect : UserInfoEffect {
        data object BackPressed : BackPressEffect
    }
}