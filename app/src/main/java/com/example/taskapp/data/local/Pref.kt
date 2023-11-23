package com.example.taskapp.data.local

import android.content.Context
import com.example.taskapp.model.Profile

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun onHide(): Boolean {
        return pref.getBoolean(HIDE_KEY, false)
    }

    fun onBoardingHide() {
        pref.edit().putBoolean(HIDE_KEY, true).apply()
    }

    fun saveProfile(name: String, description: String) {
        pref.edit().putString(PROFILE_KEY, "$name|$description").apply()
    }

    fun getProfile(): Profile {
        val profile = pref.getString(PROFILE_KEY, "") ?: ""
        val profileData = profile.split("|")
        return if (profileData.size == 2) {
            Profile(name = profileData[0], description = profileData[1])
        } else {
            Profile(name = profile)
        }
    }

    companion object {
        const val PREF_NAME = "pref_name"
        const val HIDE_KEY = "hide_key"
        const val PROFILE_KEY = "profile_key"
    }
}