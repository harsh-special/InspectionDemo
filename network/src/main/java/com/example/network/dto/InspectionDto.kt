package com.example.network.dto

data class InspectionDto(
    val inspection: InspectionData? = null
)

data class InspectionData(
    val area: Area? = null,
    val id: Int? = null,
    val inspectionType: InspectionType? = null,
    val survey: Survey? =null
)

data class Area(
    val id: Int? =null,
    val name: String?=null
)

data class InspectionType(
    val access: String?=null,
    val id: Int?=null,
    val name: String?=null
)

data class Survey(
    val categories: List<Category>?=null
)

data class Category(
    val id: Int?=null,
    val name: String?=null,
    val questions: List<Question>?=null
)

data class Question(
    val answerChoices: List<AnswerChoice>?=null,
    val id: Int?=null,
    val name: String?=null,
    val selectedAnswerChoiceId: Int? =null
)

data class AnswerChoice(
    val id: Int?=null,
    val name: String?=null,
    val score: Double?=null
)
