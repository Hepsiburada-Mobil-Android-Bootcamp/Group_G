package com.groupg.foodrecipe.ui.profile

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.groupg.foodrecipe.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(){

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        val usernameTextView: TextView = binding.usernameText
        profileViewModel.username.observe(viewLifecycleOwner, Observer {
            usernameTextView.text = it
        })

        val emailTextView: TextView = binding.emailText
        profileViewModel.email.observe(viewLifecycleOwner, Observer {
            emailTextView.text = it
        })

        val phoneTextView: TextView = binding.phoneText
        profileViewModel.phone.observe(viewLifecycleOwner, Observer {
            phoneTextView.text = it
        })

        // Profile image

        return binding.root
    }

}