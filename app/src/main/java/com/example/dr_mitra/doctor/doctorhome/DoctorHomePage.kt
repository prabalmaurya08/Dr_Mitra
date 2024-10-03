package com.example.dr_mitra.doctor.doctorhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentDoctorHomePageBinding
import com.example.dr_mitra.patient.patienthome.PatientViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class DoctorHomePage : Fragment() {
    private lateinit var binding: FragmentDoctorHomePageBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerButton:ImageButton


    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPagerAdapter: DoctorViewPagerAdapter


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

        //for Navigation Drawer
        setUpDrawer()


        //for bottom nav

        setupViewPagerWithBottomNavigation()
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun setupViewPagerWithBottomNavigation(){
        viewPager = binding.doctorHomeViewPager
        bottomNavigationView = binding.doctorBottomNav

        // Set up ViewPager with FragmentStateAdapter
        viewPagerAdapter = DoctorViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

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
    private fun setUpDrawer(){
        drawerButton.setOnClickListener {
            drawerLayout.open()
        }
    }


}