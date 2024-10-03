package com.example.dr_mitra.doctor.doctorlogin

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

import com.example.dr_mitra.databinding.FragmentDoctorLoginBinding
import com.example.dr_mitra.userLogin.LoginViewModal
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class DoctorLogin : Fragment() {

    private lateinit var binding: FragmentDoctorLoginBinding

    private var listener: OnSignupClickListener1? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var doctorLoginViewModel: LoginViewModal
    private var isPasswordVisible = false

    // Define a Coroutine Scope
    private val loginScope = CoroutineScope(Dispatchers.Main + Job())

    
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

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
    ): View {
        binding=FragmentDoctorLoginBinding.inflate(layoutInflater)

        goToDoctorSignup()

        doctorLoginViewModel= ViewModelProvider(this)[(LoginViewModal::class.java)]

        auth = FirebaseAuth.getInstance()
        doctorLogin()
        // Inflate the layout for this fragment


        setupPasswordToggle()
        return binding.root
    }

    private fun doctorLogin(){

        binding.doctorLoginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                doctorLoginViewModel.login(email,password)
            }else{
                Toast.makeText(requireContext(),"Please enter email and password",Toast.LENGTH_SHORT).show()
            }
            doctorLoginViewModel.loginResult.observe(viewLifecycleOwner){it ->

                //Processing bar
                // Hide the login button and show the ProgressBar
                binding.doctorLoginButton.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE

                // Optionally, disable inputs to prevent further interaction
                binding.loginEmail.isEnabled = false
                binding.loginPassword.isEnabled = false

                // Start a coroutine for the 5-second delay
                loginScope.launch {
                    delay(5000) // 5000 milliseconds = 5 seconds

                    // Simulate login success and navigate to the next activity
                    binding.progressBar.visibility = View.GONE
                    binding.doctorLoginButton.visibility = View.VISIBLE

                    // Re-enable inputs
                    binding.loginEmail.isEnabled = true
                    binding.loginPassword.isEnabled = true

                    // Proceed to the next activity or perform desired navigation
                    listener?.onDoctorLoginSuccess()
                }




                it.onSuccess { userId->
                    doctorLoginViewModel.getUserRole(userId).observe(viewLifecycleOwner){userRole->
                        if(userRole=="doctor"){

                            // Notify MainActivity that login was successful
                            listener?.onDoctorLoginSuccess()
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                            // Notify the parent fragment to handle the navigation

                        } else{
                            Toast.makeText(requireContext(), "This Account Does not belong to Doctor", Toast.LENGTH_SHORT).show()
                        }

                    }


                }.onFailure {
                    Toast.makeText(requireContext(), "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
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
    @SuppressLint("ClickableViewAccessibility")
    private fun setupPasswordToggle() {
        binding.loginPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Check if the user tapped on the drawableEnd (right side)
                val drawableEnd: Drawable? = binding.loginPassword.compoundDrawablesRelative[2]
                if (event.rawX >= (binding.loginPassword.right - drawableEnd!!.bounds.width())) {
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
            binding.loginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.loginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, R.drawable.eye_slash_fill, 0
            )
        } else {
            // Show password
            binding.loginPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.loginPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, R.drawable.eye_fill, 0
            )
        }
        // Move cursor to the end of the text
        binding.loginPassword.setSelection(binding.loginPassword.text.length)
        isPasswordVisible = !isPasswordVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Cancel the coroutine to avoid memory leaks
        loginScope.cancel()
    }
    interface OnSignupClickListener1 {
        fun onDoctorSignupClicked()
        fun onDoctorLoginSuccess()
        
    }


}