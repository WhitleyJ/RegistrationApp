package com.reg.registrationapp.data.dto.sendDto

import com.reg.registrationapp.data.dto.getDto.Avatar

data class SendUpdatesUserDto(
    val avatar: Avatar? = null,
    val birthday: String? = null,
    val city: String?= null,
    val instagram: String?= null,
    val name: String?,
    val status: Boolean? = false,
    val username: String?,
    val vk: String?= null
)