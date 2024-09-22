package com.example.dr_mitra.login

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dr_mitra.doctor_login.DoctorLogin
import com.example.dr_mitra.patient_login.PatientLogin

class LoginViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> PatientLogin()
            else-> DoctorLogin()

        }
    }
}