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
import java.util.regex.Pattern

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
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        val email = binding.signInEmail.text.toString()
        val password = binding.signInPassword.text.toString()

        val emailIsValid = isValidEmail(email)
        val passwordIsValid = isValidPassword(password)

        if (emailIsValid && passwordIsValid) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val input = binding.signInTextInputEmail
        var isValid : Boolean

        when {
            email.isEmpty() -> {
                input.error = getString(R.string.email_is_required)
                isValid = false
            }
            !email.contains(".") || !email.contains("@") || email.length >= 50 -> {
                input.error = getString(R.string.email_invalid)
                isValid = false
            }
            else -> {
                input.error = null
                isValid = true
            }
        }
        return isValid
    }

    private fun isValidPassword(password: String): Boolean {
        val input = binding.signInTextInputPassword
        val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{7,40}$")
        var isValid: Boolean

        when {
            password.isEmpty() -> {
                isValid = false
                input.error = getString(R.string.password_is_required)
            }
            passwordPattern.matcher(password).matches() -> {
                isValid = true
                input.error = null
            }
            else -> when {
                password.length < 7 -> {
                    isValid = false
                    input.error = getString(R.string.short_password)
                }
                password.length > 40 -> {
                    isValid = false
                    input.error = getString(R.string.long_password)
                }
                else -> {
                    isValid = false
                    input.error = getString(R.string.password_restrictions)
                }
            }
        }
        return isValid
    }
}

