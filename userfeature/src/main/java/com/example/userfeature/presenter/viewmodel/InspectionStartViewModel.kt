package com.example.userfeature.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Response
import com.example.domain.model.SurveyQuestion
import com.example.domain.model.Questions
import com.example.domain.usecase.inspection.GetNewInspectionUseCase
import com.example.domain.usecase.inspection.SaveInspectionState
import com.example.userfeature.presenter.user.intent.InspectionStartIntent
import com.example.userfeature.presenter.user.state.QuestionnaireState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InspectionStartViewModel @Inject constructor(
    private val inspectionUseCase: GetNewInspectionUseCase,
    private val saveInspectionState: SaveInspectionState
) : ViewModel() {

    private val _intents = MutableLiveData<InspectionStartIntent>()
    val intents: LiveData<InspectionStartIntent> = _intents

    private val _state = MutableLiveData<QuestionnaireState>()
    val state: LiveData<QuestionnaireState> = _state


    fun processIntents(intent: InspectionStartIntent) {
        when (intent) {
            is InspectionStartIntent.StartNewInspection -> startNewInspection()
            is InspectionStartIntent.SaveQuestionnaire -> saveQuestionairee(intent.data)
        }
    }

    private fun startNewInspection() {
        _state.value = QuestionnaireState.Loading(true)
        viewModelScope.launch {
            val state = inspectionUseCase()
            _state.value =
                when (state) {
                    is Response.Loading -> QuestionnaireState.Loading(false)
                    is Response.Success -> QuestionnaireState.Success(state.data)
                    is Response.Error -> QuestionnaireState.Error(
                        state.exception.localizedMessage ?: "Something went wrong"
                    )
                }
            _state.value = QuestionnaireState.Loading(false)
        }
    }

    fun saveQuestionairee(question: Questions?) {
        saveInspectionState.invoke(question)
    }

    fun calculateScore(surveyQuestions: List<SurveyQuestion>?): Double? {
        return surveyQuestions?.fold(0.0) { acc, question ->
            val score = question.answers.firstOrNull {
                it.id == question.selectedAnswerChoiceId
            }?.score ?: 0.0

            acc + score
        }
    }
}
