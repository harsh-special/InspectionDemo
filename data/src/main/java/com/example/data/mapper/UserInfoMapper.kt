package com.example.data.mapper

import com.example.domain.model.UserInfo
import com.example.network.dto.Address
import com.example.network.dto.UserInfoDto

class UserInfoMapper : Mapper<UserInfoDto?, UserInfo> {
    override fun map(userInfoDto: UserInfoDto?): UserInfo {
        return UserInfo(
            name = userInfoDto?.name.orEmpty(),
            email = userInfoDto?.email.orEmpty(),
            username = userInfoDto?.username.orEmpty(),
            address = mapAddress(userInfoDto?.address)
        )
    }

    private fun mapAddress(addressDto: Address?): UserInfo.Address {
        return UserInfo.Address(
            zipcode = addressDto?.zipcode.orEmpty(),
            city = addressDto?.city.orEmpty(),
            street = addressDto?.street.orEmpty()
        )
    }
}
