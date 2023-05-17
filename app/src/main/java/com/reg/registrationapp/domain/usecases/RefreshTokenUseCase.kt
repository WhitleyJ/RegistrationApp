package com.reg.registrationapp.domain.usecases

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.GetRefreshedTokenDto
import com.reg.registrationapp.data.dto.sendDto.SendRefreshTokenDto
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(private val repository: RegistrationRepository
) {
    suspend operator fun invoke (access: String, token: SendRefreshTokenDto) : ApiResponse<GetRefreshedTokenDto> {
        return repository.refreshToken(access, token)
    }
}