package com.example.dr_mitra.doctor.doctorlogin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.dr_mitra.databinding.FragmentDoctorLoginBinding
import com.example.dr_mitra.userLogin.LoginViewModal
import com.google.firebase.auth.FirebaseAuth


class DoctorLogin : Fragment() {

    private lateinit var binding: FragmentDoctorLoginBinding

    private var listener: OnSignupClickListener1? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var doctorLoginViewModel: LoginViewModal



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignupClickListener1) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnSignupClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDoctorLoginBinding.inflate(layoutInflater)

        goToDoctorSignup()

        doctorLoginViewModel= ViewModelProvider(this)[(LoginViewModal::class.java)]

        auth = FirebaseAuth.getInstance()
        doctorLogin()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun doctorLogin(){

        binding.doctorLoginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                doctorLoginViewModel.login(email,password)
            }
            doctorLoginViewModel.loginResult.observe(viewLifecycleOwner){
                it.onSuccess {
                        userId->
                    doctorLoginViewModel.getUserRole(userId).observe(viewLifecycleOwner){userRole->
                        if(userRole=="doctor"){

                            // Notify MainActivity that login was successful
                            listener?.onDoctorLoginSuccess()
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                            // Notify the parent fragment to handle the navigation

                        }

                    }


                }
            }
        }




    }

    private fun goToDoctorSignup(){

        binding.doctorSignupTv.setOnClickListener {
            // Notify the parent fragment to handle the navigation
            listener?.onDoctorSignupClicked()
        }

    }

    interface OnSignupClickListener1 {
        fun onDoctorSignupClicked()
        fun onDoctorLoginSuccess()
    }


}