package com.example.data.mapper

import com.example.domain.model.User
import com.example.network.dto.UserDto

class UserMapper : Mapper<List<UserDto?>, List<User>> {
    override fun map(usersDto: List<UserDto?>): List<User> {
        return usersDto.mapNotNull(::mapUserDtoToUser)
    }

    private fun mapUserDtoToUser(userDto: UserDto?): User? {
        return userDto?.let {
            User(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                email = it.email.orEmpty()
            )
        }
    }
}
