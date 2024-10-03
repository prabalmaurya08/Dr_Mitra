package com.example.dr_mitra.doctor.doctorhome

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dr_mitra.doctor.doctorhome.doctorappointment.DoctorAppointment
import com.example.dr_mitra.doctor.doctorhome.doctorchat.DoctorChat
import com.example.dr_mitra.doctor.doctorhome.doctordashboard.DoctorDashboard
import com.example.dr_mitra.doctor.doctorhome.doctorprofile.DoctorProfile



class DoctorViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        // Number of fragments for ViewPager2
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        // Return the appropriate fragment for each position
        return when (position) {
            0 -> DoctorDashboard()

            1 -> DoctorAppointment()

            2 -> DoctorChat()
            3 -> DoctorProfile()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}