package com.example.data.repository

import com.example.core.Response
import com.example.data.datasource.userinfo.UserInfoDataSource
import com.example.data.mapper.UserInfoMapper
import com.example.domain.model.UserInfo
import com.example.domain.repository.UserInfoRepository
import com.example.network.coroutine.Dispatcher
import com.example.network.dto.UserInfoDto
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class UserInfoRepositoryImplTest {

    private lateinit var userInfoRepository: UserInfoRepository

    private lateinit var userInfoMapper: UserInfoMapper
    private lateinit var userInfoDataSource: UserInfoDataSource
    private lateinit var userInfoDto: UserInfoDto
    private lateinit var userInfo: UserInfo
    private lateinit var dispatcher: Dispatcher

    @Before
    fun setUp() {
        userInfoDto = mockk()
        userInfoDataSource = mockk()
        userInfoMapper = mockk()
        userInfo = mockk()
        userInfoDto = mockk()
        dispatcher = mockk()
        userInfoRepository =
            UserInfoRepositoryImpl(userInfoDataSource, createMockDispatcher(), userInfoMapper)
    }

    private fun createMockDispatcher(): Dispatcher {
        return object : Dispatcher {
            override val main = Dispatchers.Unconfined
            override val io = Dispatchers.IO
        }
    }

    @Test
    fun `Given successful getUserInfo API call, When getUserInfo is invoked, Then it should return Success`() =
        runTest {
            coEvery { userInfoDataSource.getUserInfo(TEST_USER_ID) } returns userInfoDto
            every { userInfoMapper.map(userInfoDto) } returns userInfo

            val result = userInfoRepository.getUserInfo(TEST_USER_ID)

            // Assert the success state
            assertTrue(result is Response.Success)
        }


    @Test
    fun `Given a network error in getUserInfo API call, When getUserInfo is invoked, Then it should show Error`() =
        runTest {
            val expectedException = IOException(NETWORK_ERROR_MESSAGE)

            coEvery { userInfoDataSource.getUserInfo(TEST_USER_ID) } throws expectedException

            val result = userInfoRepository.getUserInfo(TEST_USER_ID)

            assertTrue(result is Response.Error)
        }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private companion object {
        private const val TEST_USER_ID = "1"
        private const val NETWORK_ERROR_MESSAGE = "Network error"
    }
}

