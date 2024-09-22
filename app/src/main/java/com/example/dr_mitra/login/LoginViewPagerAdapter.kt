package com.example.dr_mitra.login

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dr_mitra.doctor.doctorlogin.DoctorLogin
import com.example.dr_mitra.patient.patientlogin.PatientLogin

class LoginViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> PatientLogin()
            else-> DoctorLogin()

        }
    }
}