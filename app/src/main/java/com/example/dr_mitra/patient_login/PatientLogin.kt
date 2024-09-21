package com.example.dr_mitra.patient_login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientLoginBinding


class PatientLogin : Fragment() {
    private lateinit var binding: FragmentPatientLoginBinding


    private var listener: OnSignupClickListener? = null


    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSignupClickListener) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnSignupClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPatientLoginBinding.inflate(layoutInflater)
        goToPatientSignup()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun goToPatientSignup() {
        binding.patientSignupTv.setOnClickListener {
            // Notify the parent fragment to handle the navigation
            listener?.onPatientSignupClicked()
        }
    }


    interface OnSignupClickListener {
        fun onPatientSignupClicked()
    }

}

