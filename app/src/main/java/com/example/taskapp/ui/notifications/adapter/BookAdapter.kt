package com.example.taskapp.ui.notifications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.databinding.ItemTaskBinding
import com.example.taskapp.model.Book
import com.example.taskapp.model.Task

class BookAdapter : Adapter<BookAdapter.BookViewHolder>() {

    private val list = arrayListOf<Book>()

    @SuppressLint("NotifyDataSetChanged")
    fun addBooks(newList: List<Book>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class BookViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.tvHomeTitle.text = book.name
            binding.tvHomeDescription.text = book.author
            if (book.author?.isNotEmpty() == true) {
                binding.tvHomeDescription.text = book.author
            } else {
                binding.tvHomeDescription.text = "Неизвестный автор"
            }
        }
    }
}