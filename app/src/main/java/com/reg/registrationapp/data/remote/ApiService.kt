package com.reg.registrationapp.data.remote

import com.reg.registrationapp.core.util.Constance.AUTH_TOKEN
import com.reg.registrationapp.core.util.Constance.CHECK_AUTH_CODE
import com.reg.registrationapp.core.util.Constance.REFRESH_TOKEN
import com.reg.registrationapp.core.util.Constance.REGISTRATION
import com.reg.registrationapp.core.util.Constance.SEND_AUTH_CODE
import com.reg.registrationapp.core.util.Constance.USER_ME
import com.reg.registrationapp.data.dto.getDto.AllUserInfoDto
import com.reg.registrationapp.data.dto.getDto.*
import com.reg.registrationapp.data.dto.sendDto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(SEND_AUTH_CODE)
    suspend fun sendAuthCode(@Body phone: SendPhoneDto): Response<GetAuthCodeDto>

    @POST(CHECK_AUTH_CODE)
    suspend fun checkAuthCode(@Body user: SendAuthCodeDto): Response<CheckAuthDto>

    @POST(REGISTRATION)
    suspend fun registration(@Body authCode: SendUserRegisterInfoDto): Response<GetUserRegisterInfoDto>

    @GET(USER_ME)
    suspend fun getUserInfo(@Header(AUTH_TOKEN) token: String): Response<AllUserInfoDto>

    @PUT(USER_ME)
    suspend fun updateUserInfo(
        @Header(AUTH_TOKEN) token: String,
        @Body updatedInfo: SendUpdatesUserDto,
    ): Response<UpdatedUserDto>

    @POST(REFRESH_TOKEN)
    suspend fun refreshToken(
        @Header(AUTH_TOKEN) tokenAccess: String,
        @Body token: SendRefreshTokenDto,
    ): Response<GetRefreshedTokenDto>

}