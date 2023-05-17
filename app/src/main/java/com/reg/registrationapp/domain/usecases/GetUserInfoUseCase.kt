package com.reg.registrationapp.domain.usecases

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.AllUserInfoDto
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend operator fun invoke(token: String): ApiResponse<AllUserInfoDto> {
        return repository.getUserInfo(token)
    }
}