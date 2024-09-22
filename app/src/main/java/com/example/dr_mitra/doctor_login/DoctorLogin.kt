package com.example.dr_mitra.doctor_login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dr_mitra.databinding.FragmentDoctorLoginBinding



class DoctorLogin : Fragment() {

    private lateinit var binding: FragmentDoctorLoginBinding

    private var listener: OnSignupClickListener1? = null


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
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun goToDoctorSignup(){

        binding.doctorSignupTv.setOnClickListener {
            // Notify the parent fragment to handle the navigation
            listener?.onDoctorSignupClicked()
        }

    }

    interface OnSignupClickListener1 {
        fun onDoctorSignupClicked()
    }


}