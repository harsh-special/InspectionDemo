package com.example.data.datasource.user

import com.example.data.datasource.userinfo.UserInfoDataSource
import com.example.data.datasource.userinfo.UserInfoDataSourceImpl
import com.example.network.dto.UserInfoDto
import com.example.network.service.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserInfoDataSourceTest {

    private lateinit var userInfoDataSource: UserInfoDataSource
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        apiService = mockk()
        userInfoDataSource = UserInfoDataSourceImpl(apiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given a call to retrieve user info, When getUserInfo is invoked, Then verify that the ApiService's getUserInfo method is called`() =
        runTest {
            coEvery { apiService.getUserInfo(TEST_USER_ID) } returns UserInfoDto()

            userInfoDataSource.getUserInfo(TEST_USER_ID)

            coVerify {
                apiService.getUserInfo(TEST_USER_ID)
            }
        }

    companion object {
        private const val TEST_USER_ID: String = "1"
    }
}