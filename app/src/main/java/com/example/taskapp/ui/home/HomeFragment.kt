package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.model.Task
import com.example.taskapp.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskAdapter(this::initAlertDialog)
        binding.rvHomeList.adapter = adapter
        getTask()
        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun initAlertDialog(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_title))
            .setMessage(getString(R.string.delete_message))
            .setPositiveButton(getString(R.string.delete_pos)) { _, _ ->
                deleteTask(task)
            }.setNegativeButton(getString(R.string.delete_neg), null).create()
        alertDialog.show()
    }

    private fun deleteTask(task: Task) {
        App.db.taskDao().delete(task)
        getTask()
    }

    private fun getTask() {
        val data = App.db.taskDao().getTask()
        adapter.addTasks(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}