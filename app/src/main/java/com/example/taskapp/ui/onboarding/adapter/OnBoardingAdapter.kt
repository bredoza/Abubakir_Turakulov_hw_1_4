package com.example.taskapp.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.taskapp.R
import com.example.taskapp.databinding.ItemOnboardingBinding
import com.example.taskapp.model.Onboarding

class OnboardingAdapter(private val onClick: () -> Unit, private val onNext: (Int) -> Unit) :
    Adapter<OnboardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        Onboarding(
            R.raw.animone,
            "Что такое Taskes?",
            "Приложение создано в качестве домашнего задания с одной важной функцией: создание заметок, задач или же целей."
        ), Onboarding(
            R.raw.animtwo,
            "Заметки?",
            "Заметки состоят из двух частей: заголовка для наименования и\nописания для основной информации."
        ), Onboarding(
            R.raw.animone,
            "Как создать?",
            "В углу экрана находится кнопка создания заметки. Нажмите на неё, чтобы создать заметку."
        ), Onboarding(
            R.raw.animtwo,
            "Что дальше?",
            "После нажатия на кнопку создания вас перенесет на экран с полями для ввода заметки. В поле 1 введите заголовок, а в поле 2 - описание."
        ), Onboarding(
            R.raw.animone,
            "Сохранение",
            "После ввода всех необходимых данных для сохранения нажмите кнопку SAVE, чтобы сохранить заметку."
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(boarding: Onboarding, position: Int) {
            binding.tvOnboardingTitle.text = boarding.title
            binding.tvOnboardingDescription.text = boarding.description
            binding.btnGetStart.isVisible = position == list.lastIndex
            binding.btnSkip.isVisible = position != list.lastIndex
            binding.btnNext.isVisible = position != list.lastIndex
            binding.btnGetStart.setOnClickListener { onClick() }
            binding.btnSkip.setOnClickListener { onClick() }
            binding.btnNext.setOnClickListener {
                onNext(position)
            }
            showLottieAnimation(boarding.lottieAnimationView)
        }

        private fun showLottieAnimation(animationRes: Int) {
            binding.animationView.setAnimation(animationRes)
            binding.animationView.playAnimation()
        }
    }
}