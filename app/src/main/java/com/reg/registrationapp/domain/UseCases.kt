package com.reg.registrationapp.domain

import com.reg.registrationapp.domain.usecases.*
import javax.inject.Inject

data class UseCases @Inject constructor(
    val checkAuthCodeUseCase: CheckAuthCodeUseCase,
    val getUserInfoUseCase: GetUserInfoUseCase,
    val refreshTokenUseCase: RefreshTokenUseCase,
    val registrationUseCase: RegistrationUseCase,
    val sendAuthCodeUseCase: SendAuthCodeUseCase,
    val updateUserInfoUseCase: UpdateUserInfoUseCase
)
