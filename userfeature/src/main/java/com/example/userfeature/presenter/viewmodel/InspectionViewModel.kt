package com.example.userfeature.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.inspection.LoadInspectionUseCase
import com.example.userfeature.presenter.utils.SingleLiveEvent
import com.example.userfeature.presenter.user.effect.LoginEffect
import com.example.userfeature.presenter.user.intent.InspectionIntent
import com.example.userfeature.presenter.user.state.InspectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// InspectionViewModel.kt

@HiltViewModel
class InspectionViewModel @Inject constructor(
    private val loadInspectionUseCase: LoadInspectionUseCase
) : ViewModel() {

    private val _state = MutableLiveData<InspectionState>()
    val state: LiveData<InspectionState> = _state

    private val _effect = SingleLiveEvent<LoginEffect>()
    val effect: SingleLiveEvent<LoginEffect> = _effect

    fun processIntents(intent: InspectionIntent) {
        when (intent) {
            is InspectionIntent.StartNewInspection -> startNewInspection()
            is InspectionIntent.LoadInspections -> loadInspections()
        }
    }

    private fun loadInspections() {
        val inspections = loadInspectionUseCase.invoke()
        _state.value = InspectionState.LoadQuestions(inspections)
    }

    private fun startNewInspection() {
        _effect.value = LoginEffect.NavigationEffect.NavigateInspectionScreen(null)
    }
}
