package com.reg.registrationapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reg.registrationapp.core.baseDataSource.ApiResponse.Error
import com.reg.registrationapp.core.baseDataSource.ApiResponse.Success
import com.reg.registrationapp.core.pref.SharedPreferences
import com.reg.registrationapp.data.dto.getDto.*
import com.reg.registrationapp.data.dto.sendDto.*
import com.reg.registrationapp.domain.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val useCases: UseCases,
    private val pref: SharedPreferences,
) : ViewModel() {

    private val _registrationState: MutableLiveData<Result<GetUserRegisterInfoDto>> =
        MutableLiveData()
    val registrationState: LiveData<Result<GetUserRegisterInfoDto>> = _registrationState

    private val _userInfo: MutableLiveData<Result<AllUserInfoDto>> = MutableLiveData()
    val userInfo: LiveData<Result<AllUserInfoDto>> = _userInfo

    private val _token: MutableLiveData<Result<GetRefreshedTokenDto>> = MutableLiveData()
    val token: LiveData<Result<GetRefreshedTokenDto>> = _token

    private val _checkAuth: MutableLiveData<Result<CheckAuthDto>> = MutableLiveData()
    val checkAuth: LiveData<Result<CheckAuthDto>> = _checkAuth

    private val _sendAuth: MutableLiveData<Result<GetAuthCodeDto>> = MutableLiveData()
    val sendAuth: LiveData<Result<GetAuthCodeDto>> = _sendAuth

    private val _updatedUser: MutableLiveData<Result<UpdatedUserDto>> = MutableLiveData()
    val updatedUser: LiveData<Result<UpdatedUserDto>> = _updatedUser

    private val _checkRegistration: MutableLiveData<Boolean> = MutableLiveData()
    val checkRegistration: LiveData<Boolean> = _checkRegistration

    fun updateToken() = viewModelScope.launch(Dispatchers.IO) {
        pref.getAuthToken()?.let { token ->
            pref.getRefreshedToken()?.let { refreshToken ->
                useCases.refreshTokenUseCase("Bearer $token", SendRefreshTokenDto(refreshToken))
                    .also {
                        when (it) {
                            is Success -> _token.postValue(Result.Success(it.data))
                            is Error -> _token.postValue(Result.Error(it.errorMessage))
                        }
                    }
            }
        }
    }

    fun checkAuthCode(user: SendAuthCodeDto) = viewModelScope.launch(Dispatchers.IO) {
        useCases.checkAuthCodeUseCase(user).also {
            when (it) {
                is Success -> {
                    _checkAuth.postValue(Result.Success(it.data))
//                    pref.saveAuthToken(it.data.access_token)
//                    pref.saveRefreshedToken(it.data.refresh_token)
                }
                is Error -> _checkAuth.postValue(Result.Error(it.errorMessage))
            }
        }
    }

    fun getUserInfo() = viewModelScope.launch(Dispatchers.IO) {
        pref.getAuthToken()?.let { token ->
            useCases.getUserInfoUseCase("Bearer $token").also {
                _userInfo.postValue(Result.Loading)
                when (it) {
                    is Success -> _userInfo.postValue(Result.Success(it.data))
                    is Error -> _userInfo.postValue(Result.Error(it.errorMessage))
                }
            }
        }
    }

    fun registrationUser(regInfo: SendUserRegisterInfoDto) = viewModelScope.launch(Dispatchers.IO) {
        useCases.registrationUseCase(regInfo).also {
            _registrationState.postValue(Result.Loading)
            when (it) {
                is Success -> {
                    _registrationState.postValue(Result.Success(it.data))
                    pref.saveAuthToken(it.data.access_token)
                    pref.saveRefreshedToken(it.data.refresh_token)
                }
                is Error -> _registrationState.postValue(Result.Error(it.errorMessage))
            }
        }
    }

    fun sendAuthCode(phone: SendPhoneDto) = viewModelScope.launch(Dispatchers.IO) {
        useCases.sendAuthCodeUseCase(phone).also {
            when (it) {
                is Success -> _sendAuth.postValue(Result.Success(it.data))
                is Error -> _sendAuth.postValue(Result.Error(it.errorMessage))
            }
        }
    }

    fun updateUserInfo(updatedUser: SendUpdatesUserDto) = viewModelScope.launch(Dispatchers.IO) {
        pref.getAuthToken()?.let { token ->
            Log.d("werewrwe", token)
            useCases.updateUserInfoUseCase("Bearer $token", updatedUser).also {
                when (it) {
                    is Success -> _updatedUser.postValue(Result.Success(it.data))
                    is Error -> _updatedUser.postValue(Result.Error(it.errorMessage))
                }
            }
        }
    }

    fun checkRegistration(check: Boolean) {
        _checkRegistration.postValue(check)
    }

    sealed class Result<out T : Any> {
        data class Success<out T : Any>(val data: T) : Result<T>()
        data class Error(val errorMessage: String) : Result<Nothing>()
        object Loading : Result<Nothing>()
    }
}