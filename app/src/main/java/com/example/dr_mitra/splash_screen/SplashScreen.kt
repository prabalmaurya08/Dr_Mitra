package com.example.dr_mitra.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentSplashScreenBinding


class SplashScreen : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)


        Handler(Looper.getMainLooper())
            .postDelayed({
               // findNavController().navigate(R.id.action_splashScreen_to_onboardingFragment)
               // findNavController().navigate(R.id.action_splashScreen_to_patientHomePage)
                findNavController().navigate(R.id.action_splashScreen_to_login)
            },3000)

        // Inflate the layout for this fragment
        return binding.root
    }

}