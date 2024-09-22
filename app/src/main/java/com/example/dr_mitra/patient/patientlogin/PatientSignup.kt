package com.example.dr_mitra.patient.patientlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientSignupBinding
import com.example.dr_mitra.mainUser.User


class PatientSignup : Fragment() {
   private lateinit var binding: FragmentPatientSignupBinding

    private lateinit var viewModel: PatientSignUpViewModal
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


        viewModel = ViewModelProvider(this)[PatientSignUpViewModal::class.java]
        firebaseSignUp()
        return binding.root
    }
    private fun firebaseSignUp() {
            binding.signupPatientButton.setOnClickListener {
                val name = binding.patientSignupName.text.toString()
                val email = binding.patientSignupEmail.text.toString()
                val password = binding.patientSignupPassword.text.toString()
                val patientUser = User(email, password, name, "patient")
                viewModel.signUpPatient(patientUser)

            }
        viewModel.signupResult.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_patientSignup_to_login)
            }
            else{
                binding.patientSignupEmail.error = "Email Already Exists"
            }
        }

    }




    private fun backToLogin(){
        val backButton=binding.signupPatientBack
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_patientSignup_to_login)
        }
    }

}