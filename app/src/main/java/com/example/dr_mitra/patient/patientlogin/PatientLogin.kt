package com.example.dr_mitra.patient.patientlogin

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import com.example.dr_mitra.R

import com.example.dr_mitra.databinding.FragmentPatientLoginBinding
import com.example.dr_mitra.userLogin.LoginViewModal
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PatientLogin : Fragment() {
    private lateinit var binding: FragmentPatientLoginBinding


    private var listener: OnSignupClickListener? = null


    private lateinit var auth :FirebaseAuth

    private lateinit var patientLoginViewModel: LoginViewModal

    private var isPasswordVisible = false


    // Define a Coroutine Scope
    private val loginScope = CoroutineScope(Dispatchers.Main + Job())


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


        setupPasswordToggle()
        // Inflate the layout for this fragment
        return binding.root
    }



    private fun patientLogin(){
        binding.patientLoginButton.setOnClickListener {


            val email = binding.patientLoginEmail.text.toString().trim()
            val password = binding.patientLoginPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){


                patientLoginViewModel.login(email,password)

            }else{
                Toast.makeText(requireContext(),"Please enter email and password",Toast.LENGTH_SHORT).show()
            }

            patientLoginViewModel.loginResult.observe(viewLifecycleOwner){ it ->
                // Hide the login button and show the ProgressBar
                binding.patientLoginButton.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE

                // Optionally, disable inputs to prevent further interaction
                binding.patientLoginEmail.isEnabled = false
                binding.patientLoginPassword.isEnabled = false

                // Start a coroutine for the 5-second delay
                loginScope.launch {
                    delay(5000) // 5000 milliseconds = 5 seconds

                    // Simulate login success and navigate to the next activity
                    binding.progressBar.visibility = View.GONE
                    binding.patientLoginButton.visibility = View.VISIBLE

                    // Re-enable inputs
                    binding.patientLoginEmail.isEnabled = true
                    binding.patientLoginPassword.isEnabled = true

                    // Proceed to the next activity or perform desired navigation
                    listener?.onPatientLoginSuccess()
                }


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
                    Toast.makeText(requireContext(), "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
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


    @SuppressLint("ClickableViewAccessibility")
    private fun setupPasswordToggle() {
        binding.patientLoginPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Check if the user tapped on the drawableEnd (right side)
                val drawableEnd: Drawable? = binding.patientLoginPassword.compoundDrawablesRelative[2]
                if (event.rawX >= (binding.patientLoginPassword.right - drawableEnd!!.bounds.width())) {
                    togglePasswordVisibility()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            binding.patientLoginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.patientLoginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, R.drawable.eye_slash_fill, 0
            )
        } else {
            // Show password
            binding.patientLoginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.patientLoginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, R.drawable.eye_fill, 0
            )
        }
        // Move cursor to the end of the text
        binding.patientLoginPassword.setSelection(binding.patientLoginPassword.text.length)
        isPasswordVisible = !isPasswordVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Cancel the coroutine to avoid memory leaks
        loginScope.cancel()
    }

    interface OnSignupClickListener {
        fun onPatientSignupClicked()
        fun onPatientLoginSuccess()




    }

}

