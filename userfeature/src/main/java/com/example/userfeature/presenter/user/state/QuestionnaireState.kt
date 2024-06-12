package com.example.userfeature.presenter.user.state

import com.example.domain.model.Questions

sealed class QuestionnaireState {
    data class Loading(val isLoading: Boolean) : QuestionnaireState()
    data class Success(val question: Questions) : QuestionnaireState()
    data class Error(val message: String) : QuestionnaireState()
}