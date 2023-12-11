package com.example.taskapp.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.model.Profile

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUri = result.data?.data
                binding.ivAvatar.setImageURI(imageUri)
            }
        }

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

        savedProfile?.name?.takeIf { it.isNotEmpty() }?.let { updateProfileData(savedProfile) }
        imageUri?.let { binding.ivAvatar.setImageURI(it) }

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            btnEditProfile.setOnClickListener {
                findNavController().navigate(R.id.editFragment)
            }

            btnExit.setOnClickListener {
                findNavController().navigate(R.id.phoneFragment)
            }

            ivAvatar.setOnClickListener {
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getContent.launch(intent)
    }

    private fun updateProfileData(profile: Profile) {
        binding.tvName.text = profile.name
        binding.tvDescription.text = profile.description
    }
}