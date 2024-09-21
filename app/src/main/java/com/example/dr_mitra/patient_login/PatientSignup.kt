package com.example.dr_mitra.patient_login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientSignupBinding


class PatientSignup : Fragment() {
   private lateinit var binding: FragmentPatientSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientSignupBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        backToLogin()
        return binding.root
    }
    private fun backToLogin(){
        val backButton=binding.signupPatientBack
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_patientSignup_to_login)
        }
    }

}