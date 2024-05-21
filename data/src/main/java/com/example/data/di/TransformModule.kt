package com.example.data.di

import com.example.data.mapper.UserInfoMapper
import com.example.data.mapper.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class TransformModule {
    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

    @Provides
    fun provideUserInfoMapper(): UserInfoMapper = UserInfoMapper()
}