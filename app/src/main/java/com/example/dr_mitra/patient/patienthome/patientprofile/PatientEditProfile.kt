package com.example.dr_mitra.patient.patienthome.patientprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientEditProfileBinding


class PatientEditProfile : Fragment() {
        private lateinit var binding: FragmentPatientEditProfileBinding
        private lateinit var viewModel: PatientProfileViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPatientEditProfileBinding.inflate(layoutInflater)

        //for Gender spinner
        genderSpinner()

        //initialising view modal
        viewModel= ViewModelProvider(this)[PatientProfileViewModal::class.java]


        // Fetch and populate existing patient profile data
        viewModel.getPatientProfile()
        viewModel.getEmailName()

        observePatientProfile()

        //calling function

        binding.patientEditProfileSubmitButton.setOnClickListener {
            savePatientProfile()
           // findNavController().navigate(R.id.action_patientEditProfile_to_patientProfile)
            findNavController().popBackStack()
        }



        // Inflate the layout for this fragment
        return binding.root

    }



    private fun savePatientProfile() {



        // Get user inputs
        val address = binding.patientEditProfileAddress.text.toString()
        val phone = binding.patientEditProfilePhoneNumber.text.toString()
        val age = binding.patientEditProfileAge.text.toString().toIntOrNull() ?: 0
        val sleepTime = binding.patientEditProfileSleepingHour.text.toString()
        val sugarLevel = binding.patientEditProfileSugarLevel.text.toString()
        val bio=binding.patientEditProfileBio.text.toString()
        val gender = binding.patientEditProfileGenderSpinner.selectedItem.toString() // Get selected gender

        val weight = binding.patientEditProfileWeight.text.toString()
        val height = binding.patientEditProfileHeight.text.toString()
        val bloodGroup = binding.patientEditProfileBloodGroup.text.toString()
        val bloodPressure = binding.patientEditProfileBloodpressure.text.toString()
        val disease = binding.patientEditProfileDisease.text.toString()

        // Call the ViewModel to save the profile
        viewModel.savePatientProfile(address,phone, age, sugarLevel, sleepTime,bio,gender, weight, height, bloodGroup, bloodPressure, disease ) // "Normal" for sugar level, update as needed


        viewModel.saveProfileResult.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {

                Toast.makeText(requireContext(), "Profile saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed to save profile. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun genderSpinner() {
        val genders = arrayOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.patientEditProfileGenderSpinner.adapter = adapter
    }

    private fun observePatientProfile() {
        viewModel.patientEmailName.observe(viewLifecycleOwner) { emailName ->
            if (emailName != null) {
                binding.patientEditProfileName.text = emailName.email
                binding.patientEditProfileEmail.text = emailName.name
            }
        }
        viewModel.patientProfile.observe(viewLifecycleOwner) { user ->
            user?.let {
                // Populate the EditText fields with existing data
                binding.patientEditProfilePhoneNumber.setText(it.phone)
                binding.patientEditProfileAge.setText(it.age.toString())
                binding.patientEditProfileSleepingHour.setText(it.sleepTime) // Ensure you have a corresponding EditText
                binding.patientEditProfileSugarLevel.setText(it.sugarLevel)
                binding.patientEditProfileBio.setText(it.bio)
                binding.patientEditProfileAddress.setText(it.address)
              //  binding.patientEditProfileGenderSpinner.setSelection(genders.indexOf(it.gender))
                binding.patientEditProfileWeight.setText(it.weight.toString())
                binding.patientEditProfileHeight.setText(it.height.toString())
                binding.patientEditProfileBloodGroup.setText(it.bloodGroup)
                binding.patientEditProfileBloodpressure.setText(it.bloodPressure)
                binding.patientEditProfileDisease.setText(it.disease)




            }
        }
    }




}