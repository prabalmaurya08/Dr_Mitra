package com.example.dr_mitra.patient.patienthome.patientprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientProfileBinding


class PatientProfile : Fragment() {
    private lateinit var binding:FragmentPatientProfileBinding
    private lateinit var viewModel: PatientProfileViewModal




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPatientProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        viewModel= ViewModelProvider(this)[PatientProfileViewModal::class.java]

        binding.patientProfileEditButton.setOnClickListener{
            findNavController().navigate(R.id.action_patientHomePage_to_patientEditProfile)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPatientProfile()
        viewModel.patientProfile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                binding.patientProfileName.text = profile.name
                binding.patientProfileAge.text = profile.age.toString()
                binding.patientSugarLevel.text=profile.sugarLevel
                binding.patientSleepTime.text=profile.sleepTime
//                binding.patientProfileGender.text = profile.
//                binding.patientProfileDisease.text = profile.disease
            }
            }


    }

}