package com.example.taskapp.ui.onboarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.taskapp.databinding.ItemOnboardingBinding
import com.example.taskapp.model.Onboarding
import kotlin.math.roundToInt

class OnboardingAdapter(private val onClick: () -> Unit, private val onNext: (Int) -> Unit) :
    Adapter<OnboardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf(
        Onboarding(
            "https://cdn.discordapp.com/attachments/1164267593268473877/1175807833003020358/onb_1.png?ex=656c9360&is=655a1e60&hm=3b08a54c7e66106e0f82a08addd20cd379b587e3cb4e1b61a0ae1ac5009a485a&",
            "Что такое Taskes?",
            "Приложение создано в качестве домашнего задания с одной важной функцией: создание заметок, задач или же целей."
        ), Onboarding(
            "https://cdn.discordapp.com/attachments/1164267593268473877/1175807833275637780/onb_2.png?ex=656c9360&is=655a1e60&hm=9c1d32db48f1149938d8aac51dd72e79bb830b8a52b515fb6f6ef77ecff0d5be&",
            "Заметки?",
            "Заметки состоят из двух частей: заголовка для наименования и\nописания для основной информации."
        ), Onboarding(
            "https://cdn.discordapp.com/attachments/1164267593268473877/1175807833619583076/onb_3.png?ex=656c9360&is=655a1e60&hm=16bfdd494c07669a4671e18e2734a7082098bd57e2399c1b0be7ee078b7d2750&",
            "Как создать?",
            "В углу экрана находится кнопка создания заметки. Нажмите на неё, чтобы создать заметку."
        ), Onboarding(
            "https://cdn.discordapp.com/attachments/1164267593268473877/1175807833950916638/onb_4.png?ex=656c9361&is=655a1e61&hm=0321b6911818ecd67bdbfd95bed5ab5f98cd7041b4eca29a3a4a91799e1768aa&",
            "Что дальше?",
            "После нажатия на кнопку создания вас перенесет на экран с полями для ввода заметки. В поле 1 введите заголовок, а в поле 2 - описание."
        ), Onboarding(
            "https://cdn.discordapp.com/attachments/1164267593268473877/1175807834202591393/onb_5.png?ex=656c9361&is=655a1e61&hm=4fda12047aeaf183ff45d2c05ba69f4dedb9bddb61b518797492a63be4e5c394&",
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
        ViewHolder(binding.root) {

        fun bind(boarding: Onboarding, position: Int) {
            binding.tvOnboardingTitle.text = boarding.title
            binding.tvOnboardingDescription.text = boarding.description
            binding.btnGetStart.isVisible = position == list.lastIndex
            binding.btnSkip.isVisible = position != list.lastIndex
            binding.btnNext.isVisible = position != list.lastIndex
            binding.btnGetStart.setOnClickListener { onClick() }
            binding.btnSkip.setOnClickListener { onClick() }
            binding.btnNext.setOnClickListener { onNext(position) }

            Glide.with(binding.root.context).load(boarding.imageUri).apply(
                RequestOptions().transform(RoundedCorners(dpForCorner(binding.root.context, 16)))
            ).into(binding.ivOnboarding)
        }

        private fun dpForCorner(context: Context, dp: Int): Int {
            val density = context.resources.displayMetrics.density
            return (dp.toFloat() * density).roundToInt()
        }
    }
}