package com.reg.registrationapp.presentation.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.reg.registrationapp.R
import com.reg.registrationapp.core.baseFragment.BaseFragment
import com.reg.registrationapp.core.util.Extensions.navigate
import com.reg.registrationapp.databinding.FragmentRegistrationBinding
import com.reg.registrationapp.databinding.FragmentViewPagerBinding
import com.reg.registrationapp.presentation.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment :
    BaseFragment<RegistrationViewModel, FragmentViewPagerBinding>(
        FragmentViewPagerBinding::inflate
    ) {
    private val viewModel: RegistrationViewModel by activityViewModels()
    private var ctx: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun FragmentViewPagerBinding.setupViews() {
        viewPager.adapter = RootViewPagerAdapter(ctx as FragmentActivity)
        tabLayout.tabIconTint = null
        TabLayoutMediator(this.tabLayout, this.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.text = getString(R.string.auth)
                    tab.setIcon(R.drawable.img_1)
                }
                1 -> {
                    tab.text = getString(R.string.reg)
                    tab.setIcon(R.drawable.img_2)
                }
            }
        }.attach()
    }

    override fun FragmentViewPagerBinding.observeViewModel() {
        viewModel.checkRegistration.observe(viewLifecycleOwner) {
            when(it) {
                true -> navigate(R.id.action_viewPagerFragment_to_profileFragment)
                false -> viewPager.setCurrentItem(1,true)
                else -> viewPager.setCurrentItem(1,true)
            }
        }
    }
}