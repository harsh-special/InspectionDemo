package com.example.data.mapper

import android.util.Log
import com.example.core.mapper.Mapper
import com.example.domain.model.Answers
import com.example.domain.model.SurveyQuestion
import com.example.domain.model.Questions
import com.example.network.dto.InspectionDto

class InspectionMapper : Mapper<InspectionDto, Questions> {
    override fun map(inspectionDto: InspectionDto): Questions {

        val questionsDto: List<com.example.network.dto.Question>? =
            inspectionDto.inspection?.survey?.categories?.flatMap { categories ->
                categories.questions.orEmpty()
            }

        val surveyQuestionList = questionsDto?.map {
            SurveyQuestion(
                it.id ?: 0,
                it.name.orEmpty(),
                it.answerChoices?.map { answerChoice ->
                    Answers(
                        answerChoice.id ?: -1,
                        answerChoice.name.orEmpty(),
                        answerChoice.score ?: 0.0
                    )
                }.orEmpty(),
                it.selectedAnswerChoiceId
            )
        }.orEmpty()

        surveyQuestionList.map {
            Log.e("Demo", "Application final response " + it.name)
        }
        return Questions(id = inspectionDto.inspection?.id ?: -1, surveyQuestions = surveyQuestionList)
    }


}