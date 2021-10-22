package com.groupg.foodrecipe.ui.authentication.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.groupg.foodrecipe.MainActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        initButtons()
        return binding.root
    }

    private fun initButtons() {
        binding.registerSignInText.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}