package com.example.dr_mitra.doctor.doctorhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentDoctorHomePageBinding


class DoctorHomePage : Fragment() {
    private lateinit var binding: FragmentDoctorHomePageBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerButton:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDoctorHomePageBinding.inflate(layoutInflater)

        drawerLayout=binding.doctorDrawerLayout
        drawerButton=binding.doctorDrawerButton

        setUpDrawer()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun setUpDrawer(){
        drawerButton.setOnClickListener {
            drawerLayout.open()
        }
    }


}