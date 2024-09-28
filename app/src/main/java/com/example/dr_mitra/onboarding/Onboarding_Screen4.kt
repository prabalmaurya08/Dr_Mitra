package com.example.dr_mitra.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentOnboardingScreen2Binding
import com.example.dr_mitra.databinding.FragmentOnboardingScreen4Binding

class Onboarding_Screen4 : Fragment() {


    private lateinit var binding: FragmentOnboardingScreen4Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingScreen4Binding.inflate(inflater, container, false)

      binding.getStartedButtonOnboarding4.setOnClickListener {
           findNavController().navigate(R.id.action_onboardingFragment_to_login)
            onBoardingFinished()
        }
        val viewPager = activity?.findViewById<ViewPager2>(R.id.onboardingViewPagerDark)
        binding.previousButtonOnboarding4.setOnClickListener {
            viewPager?.currentItem=2
        }

        return binding.root
    }
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }
}