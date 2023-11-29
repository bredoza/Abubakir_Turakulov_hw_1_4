package com.example.taskapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.model.Profile

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileDao = App.db.profileDao()
        val savedProfile = profileDao.getProfile()

        if (savedProfile?.name?.isNotEmpty() == true) {
            updateProfileData(savedProfile)
        }

        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.editFragment)
        }
    }

    private fun updateProfileData(profile: Profile) {
        binding.tvName.text = profile.name
        binding.tvDescription.text = profile.description
    }
}