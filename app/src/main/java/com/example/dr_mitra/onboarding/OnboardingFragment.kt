package com.example.dr_mitra.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

private lateinit var onbordingItemAdapter: OnbordingItemAdapter
private lateinit var binding: FragmentOnboardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        setOnboardingItems()
    return binding.root
    }
    private fun setOnboardingItems() {
       onbordingItemAdapter = OnbordingItemAdapter(listOf<Fragment>(
           Onboarding_Screen1(),
           Onboarding_Screen2(),
           Onboarding_Screen3(),
           Onboarding_Screen4()
       ),
           requireActivity().supportFragmentManager,lifecycle
       )
       val onbordingViewPager = binding.onboardingViewPagerDark
        onbordingViewPager.adapter=onbordingItemAdapter

    }

}