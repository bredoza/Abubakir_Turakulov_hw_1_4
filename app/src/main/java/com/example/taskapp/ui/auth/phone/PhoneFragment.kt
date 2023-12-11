package com.example.taskapp.ui.auth.phone

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneFragment : Fragment() {

    private lateinit var binding: FragmentPhoneBinding

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        }

        override fun onVerificationFailed(e: FirebaseException) {
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            findNavController().navigate(R.id.confirmFragment, bundleOf(VER_KEY to verificationId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etPhone.inputType = InputType.TYPE_CLASS_NUMBER

        binding.btnSend.setOnClickListener {
            val number =
                "${binding.tilBackground.prefixText.toString()}${binding.etPhone.text.toString()}"
            val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(number).setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity()).setCallbacks(callbacks).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    companion object {
        const val VER_KEY = "ver.id.key"
    }
}