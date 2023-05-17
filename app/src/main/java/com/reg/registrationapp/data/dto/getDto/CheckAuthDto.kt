package com.reg.registrationapp.data.dto.getDto

data class CheckAuthDto(
    val access_token: String,
    val is_user_exists: Boolean,
    val refresh_token: String,
    val user_id: Int
)