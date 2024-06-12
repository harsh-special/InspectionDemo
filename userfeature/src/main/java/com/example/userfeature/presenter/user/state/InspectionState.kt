package com.example.userfeature.presenter.user.state

import com.example.domain.model.Questions

sealed class InspectionState {
    data class LoadQuestions(val questionsList: List<Questions>) : InspectionState()
}