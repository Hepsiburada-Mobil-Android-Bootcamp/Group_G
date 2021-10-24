package com.groupg.foodrecipe.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.groupg.foodrecipe.ui.onboarding.screens.FirstOnboardingFragment
import com.groupg.foodrecipe.ui.onboarding.screens.SecondOnboardingFragment
import com.groupg.foodrecipe.ui.onboarding.screens.ThirdOnboardingFragment

class OnboardingAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnboardingFragment()
            1 -> SecondOnboardingFragment()
            else -> ThirdOnboardingFragment()
        }
    }
}