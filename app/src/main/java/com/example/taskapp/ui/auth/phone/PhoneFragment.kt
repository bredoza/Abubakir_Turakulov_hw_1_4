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
        binding.etPhone.setText(getString(R.string.default_start_number))
        binding.etPhone.inputType = InputType.TYPE_CLASS_NUMBER
        binding.etPhone.filters = arrayOf(object : InputFilter {
            override fun filter(
                source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
            ): CharSequence? {
                return if (dstart < 4) dest?.subSequence(dstart, dend) else source?.subSequence(
                    start, end
                )
            }
        })

        binding.btnSend.setOnClickListener {
            val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(binding.etPhone.text.toString()).setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity()).setCallbacks(callbacks).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    companion object {
        const val VER_KEY = "ver.id.key"
    }
}