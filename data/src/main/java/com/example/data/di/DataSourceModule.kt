package com.example.data.di

import com.example.data.datasource.user.UserDataSource
import com.example.data.datasource.user.UserDataSourceImpl
import com.example.data.datasource.userinfo.UserInfoDataSource
import com.example.data.datasource.userinfo.UserInfoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {
    @Binds
    fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    fun bindUserInfoDataSource(userInfoDataSourceImpl: UserInfoDataSourceImpl): UserInfoDataSource
}