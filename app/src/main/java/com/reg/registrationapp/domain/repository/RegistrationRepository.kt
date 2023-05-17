package com.reg.registrationapp.domain.repository

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.*
import com.reg.registrationapp.data.dto.sendDto.*

interface RegistrationRepository {

    suspend fun refreshToken(access: String,token: SendRefreshTokenDto) : ApiResponse<GetRefreshedTokenDto>
    suspend fun sendAuthCode(phone: SendPhoneDto): ApiResponse<GetAuthCodeDto>
    suspend fun checkAuthCode(user: SendAuthCodeDto) : ApiResponse<CheckAuthDto>
    suspend fun registration(authCode: SendUserRegisterInfoDto) : ApiResponse<GetUserRegisterInfoDto>
    suspend fun getUserInfo(access: String) : ApiResponse<AllUserInfoDto>
    suspend fun updateUserInfo(token: String, updatedInfo: SendUpdatesUserDto) : ApiResponse<UpdatedUserDto>
}