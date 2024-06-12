package com.example.userfeature.presenter.user.effect

sealed interface LoginEffect {
    sealed interface  NavigationEffect  : LoginEffect {
        data class NavigateInspectionScreen(val id : Int?) : NavigationEffect
    }
}
