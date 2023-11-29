package com.example.taskapp.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentTaskBinding
import com.example.taskapp.model.Task

@Suppress("DEPRECATION")
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var editingTask: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editingTask = arguments?.getSerializable("task") as? Task

        if (editingTask != null) {
            binding.etTitle.setText(editingTask?.title)
            binding.etDescription.setText(editingTask?.description)
            binding.btnSave.text = getString(R.string.update)
        } else {
            binding.btnSave.text = getString(R.string.save)
        }

        binding.btnSave.setOnClickListener { checkTask() }
    }

    private fun checkTask() {
        if (binding.etTitle.text.isNotEmpty()) {
            if (editingTask != null) {
                update()
            } else {
                save()
            }
        } else {
            binding.etTitle.error = getString(R.string.fill_this_field)
        }
    }

    private fun update() {
        editingTask?.title = binding.etTitle.text.toString()
        editingTask?.description = binding.etDescription.text.toString()

        App.db.taskDao().update(editingTask!!)
        findNavController().navigateUp()
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }
}