package com.example.taskapp.ui.task

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCheck()

        binding.btnSave.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString()
            )
            setFragmentResult(TASK_RESULT_KEY, bundleOf(TASK_KEY to data))
            findNavController().navigateUp()
        }
    }

    private fun textCheck() {
        val textCheck = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateSaveButtonVisibility()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        with(binding) {
            etTitle.addTextChangedListener(textCheck)
            etDescription.addTextChangedListener(textCheck)
        }
    }

    private fun updateSaveButtonVisibility() {
        val titleLength = binding.etTitle.text.length
        val descriptionLength = binding.etDescription.text.length

        binding.btnSave.visibility = when {
            titleLength > 0 -> View.VISIBLE
            titleLength == 0 && descriptionLength > 0 -> View.GONE
            else -> View.GONE
        }
    }

    companion object {
        const val TASK_RESULT_KEY = "task_result_key"
        const val TASK_KEY = "task_key"
    }
}