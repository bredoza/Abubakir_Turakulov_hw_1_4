package com.example.taskapp.ui.home.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.databinding.ItemTaskBinding
import com.example.taskapp.model.Task

class TaskAdapter(private val onLongClickListener: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = list[position]
        holder.bind(task)

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("task", task)
            }

            it.findNavController().navigate(R.id.taskFragment, bundle)
        }
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvHomeTitle.text = task.title
            if (task.description?.isNotEmpty() == true) {
                binding.tvHomeDescription.text = task.description
            } else {
                binding.tvHomeDescription.text = "Пусто"
            }
            itemView.setOnLongClickListener {
                onLongClickListener.invoke(task)
                true
            }
        }
    }
}