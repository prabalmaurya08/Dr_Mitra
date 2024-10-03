package com.example.dr_mitra.patient.patienthome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dr_mitra.patient.patienthome.patientchat.PatientChat
import com.example.dr_mitra.patient.patienthome.patientdashboard.PatientDashboard
import com.example.dr_mitra.patient.patienthome.patientprofile.PatientProfile
import com.example.dr_mitra.patient.patienthome.patientsearchdoctor.PatientSearch



class PatientViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        // Number of fragments for ViewPager2
        return 4
    }



    override fun createFragment(position: Int): Fragment {
        // Return the appropriate fragment for each position
        return when (position) {
            0 -> PatientDashboard()

            1 -> PatientSearch()


            2 -> PatientChat()
            3 -> PatientProfile()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}
