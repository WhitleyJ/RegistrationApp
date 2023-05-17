package com.reg.registrationapp.data.dto.getDto

data class GetUserRegisterInfoDto(
    val access_token: String,
    val refresh_token: String,
    val user_id: Int
)