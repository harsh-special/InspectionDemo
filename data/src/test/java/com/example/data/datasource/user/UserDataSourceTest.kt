package com.example.data.datasource.user

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
class UserDataSourceTest {

    private lateinit var userDataSource: UserDataSource
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        apiService = mockk()
        userDataSource = UserDataSourceImpl(apiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given a call to retrieve user list, When getUsers is invoked, Then verify that the ApiService's getUsers method is called`() =
        runTest {
            coEvery { apiService.getUsers() } returns emptyList()

            userDataSource.getUsers()

            coVerify {
                apiService.getUsers()
            }
        }
}