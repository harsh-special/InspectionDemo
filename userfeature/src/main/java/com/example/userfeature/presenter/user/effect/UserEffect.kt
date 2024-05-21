package com.example.userfeature.presenter.user.effect

sealed interface UserEffect {

    sealed class NavigationEffect : UserEffect {
        data class NavigateUserInfoScreen(val userId: String?) : NavigationEffect()
    }
}