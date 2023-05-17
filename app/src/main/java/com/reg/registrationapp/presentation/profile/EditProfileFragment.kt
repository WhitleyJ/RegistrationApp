package com.reg.registrationapp.presentation.profile

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.reg.registrationapp.core.baseFragment.BaseFragment
import com.reg.registrationapp.core.util.Extensions.showToast
import com.reg.registrationapp.data.dto.sendDto.SendUpdatesUserDto
import com.reg.registrationapp.databinding.FragmentEditProfileBinding
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Error
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Success

internal class EditProfileFragment :
    BaseFragment<RegistrationViewModel, FragmentEditProfileBinding>(
        FragmentEditProfileBinding::inflate
    ) {

    private val viewModel: RegistrationViewModel by activityViewModels()


    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                avatarForSend = it
                Glide.with(requireContext()).load(it).circleCrop().into(binding?.avatar!!)
            }
        }

    override fun FragmentEditProfileBinding.setupViews() {
        avatar.setOnClickListener { registerForActivityResult.launch("image/*") }
        editedBtn.setOnClickListener { sendResult() }
    }

    private fun FragmentEditProfileBinding.sendResult() {
        viewModel.updateUserInfo(SendUpdatesUserDto(
            name = editName.text.toString(),
            username = editNickName.text.toString(),
        ))
        findNavController().popBackStack()
    }

    override fun FragmentEditProfileBinding.observeViewModel() {
        viewModel.updatedUser.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> {}
                is Error -> showToast(root.context, state.errorMessage)
                else -> Unit
            }
        }
    }

    companion object {
        var avatarForSend : Uri? = null
    }
}