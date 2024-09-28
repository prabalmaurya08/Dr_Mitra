package com.example.dr_mitra.doctor.doctorlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentDoctorSignupBinding
import com.example.dr_mitra.mainUser.User


class DoctorSignup : Fragment() {
    private lateinit var binding: FragmentDoctorSignupBinding

    private lateinit var viewModel: DoctorSignUpViewModal


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDoctorSignupBinding.inflate(inflater, container, false)

        backToLogin()

        viewModel=ViewModelProvider(this)[DoctorSignUpViewModal::class.java]

        firebaseSignUp()

        // Inflate the layout for this fragment
        return binding.root
    }
    private fun firebaseSignUp(){
        binding.doctorSignupButton.setOnClickListener {
            val name=binding.doctorSignupName.text.toString()
            val email=binding.doctorSignupEmail.text.toString()
            val password=binding.doctorSignupPassword.text.toString()
            val registerNumber=binding.doctorSignupRegisterNumber.text.toString()
            val doctorUser= User(email,password,name,"doctor",registerNumber)
            viewModel.signUpDoctor(doctorUser)


        }
        viewModel.signupResult.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(R.id.action_doctorSignup_to_login)
            }
            else{
                binding.doctorSignupEmail.error="Email Already Exists"
            }
        }
    }
    private fun backToLogin(){
        val backButton=binding.signupDoctorBack
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_doctorSignup_to_login)
        }
    }


}