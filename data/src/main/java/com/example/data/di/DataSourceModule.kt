package com.example.data.di

import com.example.data.datasource.inspection.InspectionDataSource
import com.example.data.datasource.inspection.InspectionDataSourceImpl
import com.example.data.datasource.login.LoginDataSource
import com.example.data.datasource.login.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {
    @Binds
    fun bindUserDataSource(userDataSourceImpl: LoginDataSourceImpl): LoginDataSource

    @Binds
    fun bindInspectionDataSource(inspectionDataSourceImpl: InspectionDataSourceImpl): InspectionDataSource
}