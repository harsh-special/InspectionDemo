package com.example.domain.usecase.user

import com.example.core.Response
import com.example.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class GetUserUseCaseTest {

    @MockK
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given users available, When getUserUseCase invoked, Then verify repository called`() =
        runTest {
            coEvery { userRepository.getUsers() } returns Response.Loading(true)

            GetUserUseCase(userRepository).invoke()

            coVerify {
                userRepository.getUsers()
            }
        }
}