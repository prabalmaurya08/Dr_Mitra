package com.example.dr_mitra.patient.patienthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientHomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class PatientHomePage : Fragment() {
    private  lateinit var binding: FragmentPatientHomePageBinding

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPagerAdapter: PatientViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientHomePageBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        viewPager = binding.patientViewPager
        bottomNavigationView = binding.patientBottomNav

        // Set up ViewPager with FragmentStateAdapter
        viewPagerAdapter = PatientViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        // Link ViewPager and BottomNavigationView
        setupViewPagerWithBottomNavigation()
        return binding.root
    }

    private fun setupViewPagerWithBottomNavigation() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_dashboard -> viewPager.currentItem = 0
                R.id.bottom_nav_appointment -> viewPager.currentItem = 1
                R.id.bottom_nav_chat -> viewPager.currentItem = 2
                R.id.bottom_nav_profile -> viewPager.currentItem = 3
            }
            true
        }
    }


}