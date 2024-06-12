package com.example.domain.usecase.inspection

import com.example.domain.model.Questions
import com.example.domain.repository.InspectionRepository
import javax.inject.Inject

class SaveInspectionState @Inject constructor(private val inspectionRepository: InspectionRepository) {
    operator fun invoke(questions: Questions?) = inspectionRepository.saveInspection(questions)
}