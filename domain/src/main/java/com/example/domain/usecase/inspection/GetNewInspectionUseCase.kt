package com.example.domain.usecase.inspection

import com.example.core.Response
import com.example.domain.model.Questions
import com.example.domain.repository.InspectionRepository
import javax.inject.Inject

class GetNewInspectionUseCase @Inject constructor(private val inspectionRepository: InspectionRepository) {

    suspend operator fun invoke(): Response<Questions> {
        val response = inspectionRepository.getInspection()

        if (response is Response.Success) {
            inspectionRepository.saveInspection(response.data)
        }
        return response
    }
}