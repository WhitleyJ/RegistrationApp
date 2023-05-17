package com.reg.registrationapp.presentation.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.reg.registrationapp.R
import com.reg.registrationapp.core.baseFragment.BaseFragment
import com.reg.registrationapp.core.util.Extensions
import com.reg.registrationapp.core.util.Extensions.navigate
import com.reg.registrationapp.databinding.FragmentProfileBinding
import com.reg.registrationapp.presentation.profile.EditProfileFragment.Companion.avatarForSend
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Error
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel.Result.Success

internal class ProfileFragment : BaseFragment<RegistrationViewModel, FragmentProfileBinding>(
    FragmentProfileBinding::inflate
) {
    private val viewModel: RegistrationViewModel by activityViewModels()

    override fun FragmentProfileBinding.setupViews() {
        viewModel.getUserInfo()
        editBtn.setOnClickListener { navigate(R.id.action_profileFragment_to_editProfileFragment) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun FragmentProfileBinding.observeViewModel() {
        viewModel.userInfo.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> {
                    state.data.profile_data.let { user ->
                        avatarForSend?.let {
                            Glide.with(root.context)
                                .load(it)
                                .circleCrop()
                                .into(avatar)
                        }
                        number.text = String.format(getString(R.string.number), user.phone)
                        tvNickName.text = String.format(getString(R.string.nick_name), user.username)
                        tvName.text = String.format(getString(R.string.name), user.name)
                    }
                }
                is Error -> Extensions.showToast(root.context, state.errorMessage)
                else -> Unit
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserInfo()
    }
}