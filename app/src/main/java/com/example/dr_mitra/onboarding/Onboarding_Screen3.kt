package com.example.dr_mitra.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentOnboardingScreen2Binding
import com.example.dr_mitra.databinding.FragmentOnboardingScreen3Binding

class Onboarding_Screen3 : Fragment() {

    private lateinit var binding: FragmentOnboardingScreen3Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingScreen3Binding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.onboardingViewPagerDark)
        binding.nextButtonOnboarding3.setOnClickListener {
            viewPager?.currentItem = 3
        }
        binding.skipButtonOnboarding3.setOnClickListener {
            viewPager?.currentItem=3
        }
        binding.previousButtonOnboarding3.setOnClickListener {
            viewPager?.currentItem=1
        }

        return binding.root
    }
}