package com.example.dr_mitra.doctor_login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentDoctorSignupBinding


class DoctorSignup : Fragment() {
    private lateinit var binding: FragmentDoctorSignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDoctorSignupBinding.inflate(inflater, container, false)

        backToLogin()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun backToLogin(){
        val backButton=binding.signupDoctorBack
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_doctorSignup_to_login)
        }
    }


}