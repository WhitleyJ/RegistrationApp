package com.reg.registrationapp.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reg.registrationapp.presentation.auth.AuthFragment
import com.reg.registrationapp.presentation.registration.RegistrationFragment

class RootViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AuthFragment()
            1 -> RegistrationFragment()
            else -> {
                throw RuntimeException("not found fragment")
            }
        }
    }
}