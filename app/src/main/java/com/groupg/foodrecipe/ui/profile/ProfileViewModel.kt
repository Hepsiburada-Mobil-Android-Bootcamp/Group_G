package com.groupg.foodrecipe.ui.profile

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.groupg.foodrecipe.R

class ProfileViewModel() : ViewModel(){

    private val _username = MutableLiveData<String>().apply {
        value = "Atacan Uğurlu"
    }
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>().apply {
        value = "atacanugurlu@gmail.com"
    }
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>().apply {
        value = "05555555555"
    }
    val phone: LiveData<String> = _phone

    private val _picture = MutableLiveData<Drawable>().apply {
        value = Drawable.createFromPath("app/src/main/res/drawable/profile_picture_placeholder.png")
    }
    val picture: LiveData<Drawable> = _picture



}