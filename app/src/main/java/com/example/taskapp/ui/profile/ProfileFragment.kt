package com.example.taskapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.data.local.Pref
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.model.Profile
import com.example.taskapp.ui.edit.EditFragment

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(EditFragment.PROFILE_RESULT_KEY) { _, bundle ->
            val data = bundle.getSerializable(EditFragment.PROFILE_KEY) as Profile
            updateProfileData(data)
        }

        val savedProfile = pref.getProfile()

        if (savedProfile.name!!.isNotEmpty()) {
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