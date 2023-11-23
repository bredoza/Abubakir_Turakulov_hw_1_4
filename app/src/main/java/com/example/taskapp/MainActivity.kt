package com.example.taskapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskapp.data.local.Pref
import com.example.taskapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.onHide()) navController.navigate(R.id.onBoardingFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            navView.isVisible = destination.id != R.id.onBoardingFragment
        }
        navView.setupWithNavController(navController)
    }
}