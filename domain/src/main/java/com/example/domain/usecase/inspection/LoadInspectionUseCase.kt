package com.example.domain.usecase.inspection

import com.example.domain.model.Questions
import com.example.domain.repository.InspectionRepository
import javax.inject.Inject

class LoadInspectionUseCase @Inject constructor(private val inspectionRepository: InspectionRepository) {

    operator fun invoke(): List<Questions> {
        return inspectionRepository.getSavedInspection()
    }
}