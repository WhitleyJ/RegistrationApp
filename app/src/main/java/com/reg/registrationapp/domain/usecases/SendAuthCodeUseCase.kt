package com.reg.registrationapp.domain.usecases

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.GetAuthCodeDto
import com.reg.registrationapp.data.dto.sendDto.SendPhoneDto
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class SendAuthCodeUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend operator fun invoke (phone: SendPhoneDto) : ApiResponse<GetAuthCodeDto> {
        return repository.sendAuthCode(phone)
    }
}