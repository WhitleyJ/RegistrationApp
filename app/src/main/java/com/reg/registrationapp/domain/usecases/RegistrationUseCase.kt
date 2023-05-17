package com.reg.registrationapp.domain.usecases

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.GetUserRegisterInfoDto
import com.reg.registrationapp.data.dto.sendDto.SendUserRegisterInfoDto
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend operator fun invoke (authCode: SendUserRegisterInfoDto) : ApiResponse<GetUserRegisterInfoDto> {
        return repository.registration(authCode)
    }
}