package com.example.data.datasource.inspection


import com.example.domain.model.Questions
import com.example.network.dto.InspectionDto
import retrofit2.Response

interface InspectionDataSource {

    suspend fun inspection(): Response<InspectionDto>

    fun saveInspection(data: Questions?)

    fun getSavedInspections(): List<Questions>

}