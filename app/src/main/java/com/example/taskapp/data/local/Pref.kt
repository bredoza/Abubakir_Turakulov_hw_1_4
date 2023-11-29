package com.example.taskapp.data.local

import android.content.Context

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun onHide(): Boolean {
        return pref.getBoolean(HIDE_KEY, false)
    }

    fun onBoardingHide() {
        pref.edit().putBoolean(HIDE_KEY, true).apply()
    }

    companion object {
        const val PREF_NAME = "pref_name"
        const val HIDE_KEY = "hide_key"
    }
}