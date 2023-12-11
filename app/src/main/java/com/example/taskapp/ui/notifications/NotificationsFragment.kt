package com.example.taskapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskapp.databinding.FragmentNotificationsBinding
import com.example.taskapp.model.Book
import com.example.taskapp.ui.notifications.adapter.BookAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import java.util.ArrayList

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private val adapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNot.adapter = adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                val list: List<Book> = it.toObjects(Book::class.java)
                adapter.addBooks(list)
            }.addOnFailureListener {

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}