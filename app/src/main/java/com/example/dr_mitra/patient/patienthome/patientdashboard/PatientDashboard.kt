package com.example.dr_mitra.patient.patienthome.patientdashboard

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientDashboardBinding
import com.google.android.material.navigation.NavigationView


class PatientDashboard : Fragment() {
    private  lateinit var binding: FragmentPatientDashboardBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPatientDashboardBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment



        return binding.root
    }






}