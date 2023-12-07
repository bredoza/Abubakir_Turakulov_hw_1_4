package com.example.taskapp.ui.auth.confirm

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentConfirmBinding
import com.example.taskapp.ui.auth.phone.PhoneFragment
import com.example.taskapp.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class ConfirmFragment : Fragment() {

    private lateinit var binding: FragmentConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString(PhoneFragment.VER_KEY)
        binding.etCode.inputType = InputType.TYPE_CLASS_NUMBER
        binding.btnConfirm.setOnClickListener {
            val credential =
                PhoneAuthProvider.getCredential(verId!!, binding.etCode.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnSuccessListener {
            showToast("Auth is success")
            findNavController().navigate(R.id.navigation_home)
        }.addOnFailureListener {
            showToast(it.message.toString())
        }
    }
}