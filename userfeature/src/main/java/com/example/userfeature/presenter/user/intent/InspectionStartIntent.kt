package com.example.userfeature.presenter.user.intent

import com.example.domain.model.Questions

// InspectionIntent.kt
sealed class InspectionStartIntent {
    data object StartNewInspection : InspectionStartIntent()

    data class SaveQuestionnaire(val data: Questions?) : InspectionStartIntent()
}