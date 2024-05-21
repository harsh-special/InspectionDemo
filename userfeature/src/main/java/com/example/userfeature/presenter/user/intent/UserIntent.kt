package com.example.userfeature.presenter.user.intent


sealed interface UserIntent {
    data object LoadUsers : UserIntent
    sealed interface UIIntent {
        data class ListItemClicked(val userId: String?) : UserIntent
    }
}
