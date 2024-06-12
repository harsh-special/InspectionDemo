package com.example.userfeature.presenter.user.intent

// InspectionIntent.kt
sealed class InspectionIntent {
    data object LoadInspections : InspectionIntent()
    data object StartNewInspection : InspectionIntent()
}