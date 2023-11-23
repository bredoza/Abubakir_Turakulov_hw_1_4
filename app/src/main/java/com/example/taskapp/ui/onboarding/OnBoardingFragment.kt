package com.example.taskapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.data.local.Pref
import com.example.taskapp.databinding.FragmentOnBoardingBinding
import com.example.taskapp.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private val adapter = OnBoardingAdapter(this::onClick, this::onNext)
    private lateinit var binding: FragmentOnBoardingBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager2 = binding.vpOnboarding
        viewPager2.adapter = adapter
        binding.onbDots.attachTo(viewPager2)
    }

    private fun onClick() {
        findNavController().navigate(R.id.navigation_home)
        pref.onBoardingHide()
    }

    private fun onNext(position: Int) {
        if (position < adapter.itemCount - 1) {
            binding.vpOnboarding.setCurrentItem(position + 1, true)
        }
    }
}