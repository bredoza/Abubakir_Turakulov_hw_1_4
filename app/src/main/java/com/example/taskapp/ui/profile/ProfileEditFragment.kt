package com.example.taskapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.databinding.FragmentProfileEditBinding
import com.example.taskapp.model.Profile

class ProfileEditFragment : Fragment() {

    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            if (binding.etName.text.isNotEmpty()) {
                save()
            } else {
                binding.etName.error = "Заполните это поле"
            }
        }
    }

    private fun save() {
        val name = binding.etName.text.toString()
        val description = binding.etDescription.text.toString()

        val data = Profile(name = name, description = description)

        val profileDao = App.db.profileDao()
        profileDao.insert(data)

        setFragmentResult(PROFILE_RESULT_KEY, bundleOf(PROFILE_KEY to data))
        findNavController().navigateUp()
    }

    companion object {
        const val PROFILE_RESULT_KEY = "profile_result_key"
        const val PROFILE_KEY = "profile_key"
    }
}