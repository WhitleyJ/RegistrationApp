package com.reg.registrationapp.presentation.auth

import androidx.fragment.app.activityViewModels
import com.reg.registrationapp.core.baseFragment.BaseFragment
import com.reg.registrationapp.core.util.Extensions.showToast
import com.reg.registrationapp.core.util.Extensions.visible
import com.reg.registrationapp.data.dto.sendDto.SendAuthCodeDto
import com.reg.registrationapp.data.dto.sendDto.SendPhoneDto
import com.reg.registrationapp.databinding.FragmentAuthBinding
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Error
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Success


internal class AuthFragment : BaseFragment<RegistrationViewModel, FragmentAuthBinding>(
    FragmentAuthBinding::inflate
) {

    private val viewModel : RegistrationViewModel by activityViewModels()

    override fun FragmentAuthBinding.setupViews() {
        countryCodePicker.setOnCountryChangeListener {
            val placeholder: String = "+" + countryCodePicker.selectedCountryCode
            phoneNumberEdit.hint = placeholder
        }
        registerButton.setOnClickListener { sendPhone() }
    }

    private fun FragmentAuthBinding.sendPhone() {
        viewModel.sendAuthCode(SendPhoneDto(phoneNumberEdit.text.toString()))
    }

    override fun FragmentAuthBinding.observeViewModel() {
        viewModel.sendAuth.observe(viewLifecycleOwner) { state ->
            codeFromSms.visible(state is Success)
            when(state) {
                is Success -> {
                    if(state.data.is_success) sendCode()
                    else viewModel.checkRegistration(false)
                }
                is Error -> showToast(root.context, state.errorMessage)
                else -> Unit
            }
        }

        viewModel.checkAuth.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Success -> viewModel.checkRegistration(state.data.is_user_exists)
                is Error -> showToast(root.context, state.errorMessage)
                else -> Unit
            }
        }
    }

    private fun FragmentAuthBinding.sendCode() {
        registerButton.text = "Авторизоваться"
        registerButton.setOnClickListener {
            viewModel.checkAuthCode(SendAuthCodeDto(
                code = codeFromSms.text.toString(),
                phone = phoneNumberEdit.text.toString()
            ))
        }
    }
}