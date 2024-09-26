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
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    private lateinit var toggle: ActionBarDrawerToggle



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPatientDashboardBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment


        drawerLayout=binding.patientDrawerLayout
        navView=binding.patientNavigationView

        drawerSetUp()
        return binding.root
    }

     private fun drawerSetUp() {

         toggle = ActionBarDrawerToggle(
             requireActivity(),
             drawerLayout,
             R.string.navigation_drawer_open,
             R.string.navigation_drawer_close
         )
         drawerLayout.addDrawerListener(toggle)
         toggle.syncState()



//        navView.setNavigationItemSelectedListener {menuItem->
//            when(menuItem.itemId){
//
//
//            }
//        }




    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }




}