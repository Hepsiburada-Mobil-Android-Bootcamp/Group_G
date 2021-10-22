package com.groupg.foodrecipe.ui.splash

import android.animation.Animator
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
import com.groupg.foodrecipe.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        controlSplashAnimation()
        return binding.root
    }

    private fun controlSplashAnimation() {
        binding.lottieAnimationSplash.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                if (!isOnboardingShown()) {
                    findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                }
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }
        })
    }

    private fun isOnboardingShown(): Boolean = SharedPrefManager(requireContext()).isOnboardingShown()
}