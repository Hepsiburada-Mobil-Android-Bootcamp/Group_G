package com.groupg.foodrecipe.ui.authentication.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.groupg.foodrecipe.MainActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        initButtons()
        return binding.root
    }

    private fun initButtons() {
        binding.signInRegisterText.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
        }

        binding.signInButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}