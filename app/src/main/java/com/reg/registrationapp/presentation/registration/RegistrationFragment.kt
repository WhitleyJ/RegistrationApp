package com.reg.registrationapp.presentation.registration

import androidx.fragment.app.activityViewModels
import com.reg.registrationapp.R
import com.reg.registrationapp.core.baseFragment.BaseFragment
import com.reg.registrationapp.core.util.Extensions
import com.reg.registrationapp.core.util.Extensions.navigate
import com.reg.registrationapp.core.util.Extensions.showToast
import com.reg.registrationapp.data.dto.sendDto.SendUserRegisterInfoDto
import com.reg.registrationapp.databinding.FragmentRegistrationBinding
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Error
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Success


internal class RegistrationFragment :
    BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>(
        FragmentRegistrationBinding::inflate
    ) {

    private val viewModel: RegistrationViewModel by activityViewModels()

    override fun FragmentRegistrationBinding.setupViews() {
        registerButton.setOnClickListener { sendResult() }
    }

    private fun FragmentRegistrationBinding.sendResult() {
        viewModel.registrationUser(SendUserRegisterInfoDto(
            nameEditText.text.toString(),
            phoneNumberTextView.text.toString(),
            usernameEditText.text.toString()
        ))
    }

    override fun FragmentRegistrationBinding.observeViewModel() {
        viewModel.registrationState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> navigate(R.id.action_viewPagerFragment_to_profileFragment)
                is Error -> showToast(root.context, state.errorMessage)
                else -> Unit
            }
        }
    }
}

