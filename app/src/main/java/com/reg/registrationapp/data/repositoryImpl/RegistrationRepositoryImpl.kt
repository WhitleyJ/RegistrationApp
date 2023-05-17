package com.reg.registrationapp.data.repositoryImpl

import com.reg.registrationapp.core.baseDataSource.BaseDataSource
import com.reg.registrationapp.core.pref.SharedPreferences
import com.reg.registrationapp.data.dto.sendDto.*
import com.reg.registrationapp.data.remote.ApiService
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RegistrationRepository, BaseDataSource() {

    override suspend fun refreshToken(access: String, token: SendRefreshTokenDto) = executeRequest {
        apiService.refreshToken(access, token)
    }

    override suspend fun sendAuthCode(phone: SendPhoneDto) = executeRequest {
        apiService.sendAuthCode(phone)
    }

    override suspend fun checkAuthCode(user: SendAuthCodeDto) = executeRequest {
        apiService.checkAuthCode(user)
    }

    override suspend fun registration(authCode: SendUserRegisterInfoDto) = executeRequest {
        apiService.registration(authCode)
    }

    override suspend fun getUserInfo(access: String) = executeRequest {
        apiService.getUserInfo(access)
    }

    override suspend fun updateUserInfo(token: String,updatedInfo: SendUpdatesUserDto) = executeRequest {
        apiService.updateUserInfo(token, updatedInfo)
    }
}