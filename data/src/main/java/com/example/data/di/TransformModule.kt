package com.example.data.di

import com.example.data.mapper.InspectionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class TransformModule {

    @Provides
    fun provideQuestionMapper(): InspectionMapper = InspectionMapper()
}