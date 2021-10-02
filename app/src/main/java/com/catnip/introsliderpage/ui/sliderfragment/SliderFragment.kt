package com.catnip.introsliderpage.ui.sliderfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.catnip.introsliderpage.databinding.FragmentSliderBinding

class SliderFragment(
    private val title: String,
    private val desc: String,
    private val imgUrlSlider: String
) : Fragment() {

    private lateinit var binding: FragmentSliderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSliderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataSlider()
    }

    private fun setDataSlider() {
        binding.tvTitle.text = title
        binding.tvDesc.text = desc
        context?.let {
            Glide.with(it)
                .load(imgUrlSlider)
                .into(binding.ivIntro)
        }
    }
}