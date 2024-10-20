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

        binding.patientProfileFloatingButton.setOnClickListener{
            findNavController().navigate(R.id.action_patientHomePage_to_patientEditProfile)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPatientProfile()
        viewModel.getEmailName()
        viewModel.patientEmailName.observe(viewLifecycleOwner) { emailName ->
            if (emailName != null) {
                binding.patientProfileEmail.text = emailName.email
                binding.patientProfileName.text = emailName.name
            }
        }
        viewModel.patientProfile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
            //    binding.patientProfileName.text = profile.name
                binding.patientProfileAge.text = profile.age.toString()
                binding.patientProfileAddress.text = profile.address
                binding.patientProfilePhoneNumber.text = profile.phone
                binding.patientProfileSugarLevel.text=profile.sugarLevel
                binding.patientProfileSleepingHour.text=profile.sleepTime
                binding.patientProfileGender.text = profile.gender
                binding.patientProfileDisease.text = profile.disease
                binding.patientProfileBio.text = profile.bio
                binding.patientProfileBloodGroup.text = profile.bloodGroup
                binding.patientProfileBloodPressure.text = profile.bloodPressure
                binding.patientProfileWeight.text = profile.weight.toString()
                binding.patientProfileHeight.text = profile.height.toString()



            }
            }


    }

}