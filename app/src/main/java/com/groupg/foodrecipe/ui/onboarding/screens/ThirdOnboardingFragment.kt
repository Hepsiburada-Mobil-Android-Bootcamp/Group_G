package com.groupg.foodrecipe.ui.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.groupg.foodrecipe.MainActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.data.local.SharedPrefManager
import com.groupg.foodrecipe.databinding.FragmentThirdOnboardingBinding

class ThirdOnboardingFragment : Fragment() {
    private var _binding: FragmentThirdOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonControl()
    }

    private fun buttonControl() {
        binding.thirdOnboardingButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_signInFragment)
            SharedPrefManager(requireContext()).setOnboardingShown()

        }
    }
}