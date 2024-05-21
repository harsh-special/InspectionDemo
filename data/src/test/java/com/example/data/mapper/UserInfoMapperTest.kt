package com.example.data.mapper

import com.example.domain.model.UserInfo
import com.example.network.dto.UserInfoDto
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UserInfoMapperTest(
    private val userInfoDto: UserInfoDto,
    private val expectedName: String,
    private val expectedEmail: String,
    private val expectedUsername: String,
    private val expectedZipcode: String,
    private val expectedCity: String,
    private val expectedStreet: String
) {

    private companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    UserInfoDto(),
                    "Leanne Graham",
                    "Sincere@april.biz",
                    "Bret",
                    "92998-3874",
                    "Gwenborough",
                    "Kulas Light"
                )
            )
        }
    }

    private lateinit var userInfoMapper: UserInfoMapper


    @Before
    fun setUp() {
        userInfoMapper = mockk()
        every { userInfoMapper.map(userInfoDto) } returns createExpectedUserInfo()
    }

    @Test
    fun `Given a UserInfoDto, When map is called, Then it should convert to UserInfo`() {
        val resultUserInfo = userInfoMapper.map(userInfoDto)

        assertEquals(expectedName, resultUserInfo.name)
        assertEquals(expectedEmail, resultUserInfo.email)
        assertEquals(expectedUsername, resultUserInfo.username)

        val address = resultUserInfo.address
        assertEquals(expectedZipcode, address.zipcode)
        assertEquals(expectedCity, address.city)
        assertEquals(expectedStreet, address.street)
    }

    private fun createExpectedUserInfo(): UserInfo {
        return UserInfo(
            name = expectedName,
            email = expectedEmail,
            username = expectedUsername,
            address = UserInfo.Address(
                zipcode = expectedZipcode,
                city = expectedCity,
                street = expectedStreet
            )
        )
    }
}
