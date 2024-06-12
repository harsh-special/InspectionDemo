package com.example.domain.repository

import com.example.core.Response
import com.example.domain.model.Questions

interface InspectionRepository {

    suspend fun getInspection(): Response<Questions>
    fun saveInspection(data: Questions?)

    fun getSavedInspection(): List<Questions>
}