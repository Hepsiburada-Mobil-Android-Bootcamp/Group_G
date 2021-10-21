package com.groupg.foodrecipe.ui.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.groupg.foodrecipe.MainActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.data.local.SharedPrefManager
import com.groupg.foodrecipe.databinding.FragmentFirstOnboardingBinding

class FirstOnboardingFragment : Fragment() {
    private var _binding: FragmentFirstOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstOnboardingBinding.inflate(inflater, container, false)

        buttonControl()

        return binding.root
    }

    private fun buttonControl() {
        val onBoardingViewPager = activity?.findViewById<ViewPager2>(R.id.onboardingViewPager)

        binding.firstOnboardingButton.setOnClickListener {
            onBoardingViewPager?.currentItem = 1
        }

        binding.firstSkipButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            SharedPrefManager(requireContext()).setOnboardingShown()
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}