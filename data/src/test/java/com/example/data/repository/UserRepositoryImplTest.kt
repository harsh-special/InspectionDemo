package com.example.data.repository

import com.example.core.Response
import com.example.data.datasource.user.UserDataSource
import com.example.data.mapper.UserMapper
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.network.coroutine.Dispatcher
import com.example.network.dto.UserDto
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

class UserRepositoryImplTest {

    private lateinit var userRepository: UserRepository

    private lateinit var userMapper: UserMapper
    private lateinit var userDataSource: UserDataSource
    private lateinit var usersDto: List<UserDto>
    private lateinit var users: List<User>
    private lateinit var dispatcher: Dispatcher

    @Before
    fun setUp() {
        usersDto = mockk()
        userDataSource = mockk()
        userMapper = mockk()
        users = mockk()
        usersDto = mockk()
        dispatcher = mockk()
        userRepository =
            UserRepositoryImpl(userDataSource, createMockDispatcher(), userMapper)
    }

    @Test
    fun `Given successful getUsers API call, When getUsers is invoked, Then it should return Success`() =
        runTest {
            coEvery { userDataSource.getUsers() } returns usersDto

            every { userMapper.map(usersDto) } returns users

            val result = userRepository.getUsers()
            assertTrue(result is Response.Success)
        }

    @Test
    fun `Given a network error in getUsers API call, When getUsers is invoked, Then it should handle the error`() =
        runTest {
            val expectedException = IOException(NETWORK_ERROR_MESSAGE)

            coEvery { userDataSource.getUsers() } throws expectedException

            val result = userRepository.getUsers()

            assertTrue(result is Response.Error)
        }

    private fun createMockDispatcher(): Dispatcher {
        return object : Dispatcher {
            override val main = Dispatchers.Unconfined
            override val io = Dispatchers.IO
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private companion object {
        private const val NETWORK_ERROR_MESSAGE = "Network error"
    }
}

