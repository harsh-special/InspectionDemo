package com.example.data.di

import com.example.data.repository.InspectionRepositoryImpl
import com.example.data.repository.LoginRepositoryImpl
import com.example.domain.repository.InspectionRepository
import com.example.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUserRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindInspectionRepository(inspectionRepositoryImpl: InspectionRepositoryImpl): InspectionRepository
}