package com.reg.registrationapp.domain.usecases

import com.reg.registrationapp.core.baseDataSource.ApiResponse
import com.reg.registrationapp.data.dto.getDto.UpdatedUserDto
import com.reg.registrationapp.data.dto.sendDto.SendUpdatesUserDto
import com.reg.registrationapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend operator fun invoke (token: String, updatedUser: SendUpdatesUserDto) : ApiResponse<UpdatedUserDto> {
        return repository.updateUserInfo(token, updatedUser)
    }
}