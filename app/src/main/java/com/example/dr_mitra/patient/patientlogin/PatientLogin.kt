package com.example.dr_mitra.patient.patientlogin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider

import com.example.dr_mitra.databinding.FragmentPatientLoginBinding
import com.example.dr_mitra.userLogin.LoginViewModal
import com.google.firebase.auth.FirebaseAuth


class PatientLogin : Fragment() {
    private lateinit var binding: FragmentPatientLoginBinding


    private var listener: OnSignupClickListener? = null




    
    private lateinit var auth :FirebaseAuth

    private lateinit var patientLoginViewModel: LoginViewModal
    





    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (context) {

            is OnSignupClickListener -> {
                listener=context


            }



            else -> {
                throw ClassCastException("$context must implement OnSignupClickListener")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentPatientLoginBinding.inflate(layoutInflater)

        patientLoginViewModel = ViewModelProvider(this)[(LoginViewModal::class.java)]
        goToPatientSignup()

        auth = FirebaseAuth.getInstance()
        patientLogin()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun patientLogin(){
        binding.patientLoginButton.setOnClickListener {






            val email = binding.patientLoginEmail.text.toString().trim()
            val password = binding.patientLoginPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                patientLoginViewModel.login(email,password)

            }

            patientLoginViewModel.loginResult.observe(viewLifecycleOwner){

                it.onSuccess {userId->
                    patientLoginViewModel.getUserRole(userId).observe(viewLifecycleOwner){userRole->
                        if(userRole=="patient"){
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                            // Notify MainActivity that login was successful
                           //

                            listener?.onPatientLoginSuccess()



                        }
                        else{
                            Toast.makeText(requireContext(), "This Account Does not belong to Patient", Toast.LENGTH_SHORT).show()
                        }

                        }


                }.onFailure {
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }


            }
            // Notify the parent fragment to handle the navigation

        }
    }






    private fun goToPatientSignup() {
        binding.patientSignupTv.setOnClickListener {
            // Notify the parent fragment to handle the navigation
            listener?.onPatientSignupClicked()

        }

    }



    interface OnSignupClickListener {
        fun onPatientSignupClicked()
        fun onPatientLoginSuccess()




    }

}

