package com.example.data.datasource.inspection

import android.content.SharedPreferences
import com.example.domain.model.Questions
import com.example.network.dto.InspectionDto
import com.example.network.service.ApiService
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class InspectionDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : InspectionDataSource {

    override suspend fun inspection(): Response<InspectionDto> {
        return apiService.inspectionStart()
    }

    override fun saveInspection(data: Questions?) {
        val json = gson.toJson(data)
        sharedPreferences.edit().putString(data?.id.toString(), json).apply()
    }

    override fun getSavedInspections(): List<Questions> {
        val listOfSurvey = arrayListOf<Questions>()
        val allEntries = sharedPreferences.all

        for ((_, value) in allEntries) {
            val jsonString = value as? String ?: continue
            listOfSurvey.add(gson.fromJson(jsonString, Questions::class.java))
        }

        return listOfSurvey.reversed()
    }
}