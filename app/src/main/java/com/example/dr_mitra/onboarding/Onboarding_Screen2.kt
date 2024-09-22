package com.example.dr_mitra.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentOnboardingScreen1Binding
import com.example.dr_mitra.databinding.FragmentOnboardingScreen2Binding

class Onboarding_Screen2 : Fragment() {

    private lateinit var binding: FragmentOnboardingScreen2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingScreen2Binding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.onboardingViewPagerDark)
        binding.nextButtonOnboarding2.setOnClickListener {
            viewPager?.currentItem = 2
        }
        binding.skipButtonOnboarding2.setOnClickListener {
            viewPager?.currentItem=3
        }
        binding.previousButtonOnboarding2.setOnClickListener {
            viewPager?.currentItem=0
        }

        return binding.root
    }
}