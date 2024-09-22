package com.example.dr_mitra.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentOnboardingScreen1Binding


class Onboarding_Screen1 : Fragment() {

    private lateinit var binding: FragmentOnboardingScreen1Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingScreen1Binding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.onboardingViewPagerDark)
        binding.nextButtonOnboarding1.setOnClickListener {
            viewPager?.currentItem = 1
        }

        binding.skipButtonOnboarding1.setOnClickListener {
            viewPager?.currentItem = 3
        }

        return binding.root
    }


}