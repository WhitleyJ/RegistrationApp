package com.reg.registrationapp.domain.usecases

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.CheckAuthDto
import com.reg.registrationapp.data.dto.sendDto.SendAuthCodeDto
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class CheckAuthCodeUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend operator fun invoke(user: SendAuthCodeDto) : ApiResponse<CheckAuthDto> {
        return repository.checkAuthCode(user)
    }
}