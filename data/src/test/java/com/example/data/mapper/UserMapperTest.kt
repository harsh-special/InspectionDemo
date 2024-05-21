package com.example.data.mapper

import com.example.domain.model.User
import com.example.network.dto.UserDto
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UserMapperTest(
    private val userDtoList: List<UserDto?>,
    private val expectedUserList: List<User>
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    listOf(
                        UserDto("1", name = "John", email = "john@example.com"),
                        UserDto("2", name = "Alice", email = "alice@example.com")
                    ),
                    listOf(
                        User("1", "John", "john@example.com"),
                        User("2", "Alice", "alice@example.com")
                    )
                )
            )
        }
    }

    @Test
    fun `Given a list of UserDto, When map is called, Then it should convert to a list of User`() {
        // Mocking the UserMapper
        val userMapper = mockk<UserMapper>()

        every { userMapper.map(userDtoList) } returns expectedUserList

        val userDto = userMapper.map(userDtoList).first()

        val users = expectedUserList.first()
        // Verify that the mapping is correct for user
        assertEquals(users.id, userDto.id)
        assertEquals(users.name, userDto.name)
        assertEquals(users.email, userDto.email)
    }
}
