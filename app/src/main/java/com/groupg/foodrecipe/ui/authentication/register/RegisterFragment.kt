package com.groupg.foodrecipe.ui.authentication.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.groupg.foodrecipe.MainActivity
import com.groupg.foodrecipe.R
import com.groupg.foodrecipe.databinding.FragmentRegisterBinding
import java.util.regex.Pattern

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

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
            attemptRegister()
        }
    }

    private fun attemptRegister() {
        val username = binding.registerUsername.text.toString()
        val email = binding.registerEmail.text.toString()
        val password = binding.registerPassword.text.toString()

        val usernameIsValid = isValidUsername(username)
        val emailIsValid = isValidEmail(email)
        val passwordIsValid = isValidPassword(password)

        if (emailIsValid && passwordIsValid && usernameIsValid) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        setUsername(username)
                        Toast.makeText(context, getString(R.string.register_success), Toast.LENGTH_LONG).show()
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, getString(R.string.firebase_error, exception.message), Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun setUsername(username: String) {
        val user = firebaseAuth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = username
        }
        user!!.updateProfile(profileUpdates)
    }

    private fun isValidUsername(username: String): Boolean {
        val input = binding.registerTextInputUsername
        val usernamePattern = Pattern.compile("^[a-z0-9_]{3,19}$")
        var isValid: Boolean

        when {
            username.isEmpty() -> {
                isValid = false
                input.error = getString(R.string.username_is_required)
            }
            usernamePattern.matcher(username).matches() -> {
                isValid = true
                input.error = null
            }
            else -> when {
                username.length < 3 -> {
                    isValid = false
                    input.error = getString(R.string.short_username)
                }
                username.length > 19 -> {
                    isValid = true
                    input.error = getString(R.string.long_username)
                }
                else -> {
                    isValid = false
                    input.error = getString(R.string.username_restrictions)
                }
            }
        }
        return isValid
    }

    private fun isValidPassword(password: String): Boolean {
        val input = binding.registerTextInputPassword
        val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,39}$")
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
                password.length <= 7 -> {
                    isValid = false
                    input.error = getString(R.string.short_password)
                }
                password.length >= 40 -> {
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

    private fun isValidEmail(email: String): Boolean {
        val input = binding.registerTextInputEmail
        var isValid : Boolean

        when {
            email.isEmpty() -> {
                input.error = getString(R.string.email_is_required)
                isValid = false
            }
            !email.contains(".") || !email.contains("@") || email.length >= 50 ||email.length <= 5 -> {
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
}