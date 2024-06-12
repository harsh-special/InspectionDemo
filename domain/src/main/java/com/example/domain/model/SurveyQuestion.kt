package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


enum class QuestionaireeStatus {
    PROGRESS,
    COMPLETED
}

@Parcelize
data class Questions(
    var status: QuestionaireeStatus = QuestionaireeStatus.PROGRESS,
    var score: Double? = 0.0,
    val id: Int,
    val surveyQuestions: List<SurveyQuestion>
) : Parcelable

@Parcelize
data class SurveyQuestion(
    val id: Int,
    val name: String,
    val answers: List<Answers>,
    var selectedAnswerChoiceId: Int?
) : Parcelable

@Parcelize
data class Answers(val id: Int, val name: String, val score: Double) : Parcelable
