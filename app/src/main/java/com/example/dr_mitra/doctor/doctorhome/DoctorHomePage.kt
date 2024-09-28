package com.example.dr_mitra.doctor.doctorhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentDoctorHomePageBinding


class DoctorHomePage : Fragment() {
    private lateinit var binding: FragmentDoctorHomePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDoctorHomePageBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


}