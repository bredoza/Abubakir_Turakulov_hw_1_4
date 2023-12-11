package com.example.taskapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentDashboardBinding
import com.example.taskapp.model.Book
import com.example.taskapp.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener { checkBook() }
    }

    private fun checkBook() {
        if (binding.etName.text.isNotEmpty()) {
            save()
        } else {
            binding.etName.error = getString(R.string.fill_this_field)
        }
    }

    private fun save() = with(binding) {
        val data = Book(
            name = etName.text.toString(), author = etAuthor.text.toString()
        )
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).add(data)
            .addOnSuccessListener {
                etName.text.clear()
                etAuthor.text.clear()
                showToast("Book has been saved")
            }.addOnFailureListener {
                showToast(it.message.toString())
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}