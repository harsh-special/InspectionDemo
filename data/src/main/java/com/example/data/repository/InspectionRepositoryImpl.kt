package com.example.data.repository

import com.example.core.Response
import com.example.core.safeApiCall
import com.example.data.datasource.inspection.InspectionDataSource
import com.example.data.mapper.InspectionMapper
import com.example.domain.model.Questions
import com.example.domain.repository.InspectionRepository
import com.example.network.coroutine.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InspectionRepositoryImpl @Inject constructor(
    private val inspectionDataSource: InspectionDataSource,
    private val dispatcher: Dispatcher,
    private val mapper: InspectionMapper
) : InspectionRepository {

    private val tag = javaClass.name

    override suspend fun getInspection(): Response<Questions> = withContext(dispatcher.io) {
        safeApiCall(tag, mapper) {
            inspectionDataSource.inspection()
        }
    }

    override fun saveInspection(data: Questions?) {
        return inspectionDataSource.saveInspection(data)
    }

    override fun getSavedInspection(): List<Questions> {
        return inspectionDataSource.getSavedInspections()
    }

}