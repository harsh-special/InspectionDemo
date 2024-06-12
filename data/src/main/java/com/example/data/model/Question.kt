package com.example.data.model
import com.example.domain.model.QuestionaireeStatus


data class Questions(
    var status: QuestionaireeStatus = QuestionaireeStatus.PROGRESS,
    var score: Double? = 0.0,
    val id: Int,
    val questions: List<Question>
)

data class Question(
    val id: Int,
    val name: String,
    val answerChoices: List<AnswerChoice>,
    var selectedAnswerChoiceId: Int?
)

data class AnswerChoice(val id: Int, val name: String, val score: Double)
