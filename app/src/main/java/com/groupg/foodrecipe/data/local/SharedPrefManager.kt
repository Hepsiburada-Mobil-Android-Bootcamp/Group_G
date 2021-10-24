package com.groupg.foodrecipe.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun isOnboardingShown(): Boolean = sharedPreferences.getBoolean("onboarding", false)

    fun setOnboardingShown() = sharedPreferences.edit().putBoolean("onboarding", true).apply()
}