package com.example.dr_mitra.patient.patientlogin

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
        setupObservers()
        patientLogin()


        setupPasswordToggle()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun setupObservers() {
        // Observe login result here instead of inside the click listener
        patientLoginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            handleLoginResult(result)
        }
    }





    private fun handleLoginResult(result: Result<String>) {
        // Hide progress bar and enable UI elements after login
        binding.progressBar.visibility = View.GONE
        binding.patientLoginButton.isEnabled = true
        binding.patientLoginEmail.isEnabled = true
        binding.patientLoginPassword.isEnabled = true

        result.onSuccess { userId ->
            // Handle success
            patientLoginViewModel.getUserRole(userId).observe(viewLifecycleOwner) { userRole ->
                if (userRole == "patient") {
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    listener?.onPatientLoginSuccess()
                    saveLoginSession(requireContext())
                } else {
                    Toast.makeText(requireContext(), "This account does not belong to a patient", Toast.LENGTH_SHORT).show()
                }
            }
        }.onFailure {
            // Handle failure
            Toast.makeText(requireContext(), "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
    private fun patientLogin() {
        binding.patientLoginButton.setOnClickListener {
            val email = binding.patientLoginEmail.text.toString().trim()
            val password = binding.patientLoginPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Show progress bar and disable UI elements
                binding.progressBar.visibility = View.VISIBLE
                binding.patientLoginButton.isEnabled = false
                binding.patientLoginEmail.isEnabled = false
                binding.patientLoginPassword.isEnabled = false

                patientLoginViewModel.login(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }







    private fun goToPatientSignup() {
        binding.patientSignupTv.setOnClickListener {
            // Notify the parent fragment to handle the navigation
            listener?.onPatientSignupClicked()

        }

    }

    private fun saveLoginSession(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)  // Save the login status
        editor.apply()
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

