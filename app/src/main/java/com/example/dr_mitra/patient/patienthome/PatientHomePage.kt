package com.example.dr_mitra.patient.patienthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView

import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientHomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class PatientHomePage : Fragment() {
    private  lateinit var binding: FragmentPatientHomePageBinding

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPagerAdapter: PatientViewPagerAdapter




    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var drawerButton: ImageButton



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




        // Set up Navigation Drawer
        drawerLayout = binding.patientDrawerLayout
        navView = binding.patientNavigationView

        // Set up the toolbar and drawer Button
        toolbar = binding.patientHomeToolbar
        drawerButton = binding.patientDrawerButton
        drawerSetUp()
        return binding.root
    }

    private fun drawerSetUp() {
        drawerButton.setOnClickListener {
            drawerLayout.open()
        }

//        toggle = ActionBarDrawerToggle(
//            requireActivity(),
//            drawerLayout,
//           binding.patientHomeToolbar, // Set the toolbar
//            R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()



//        navView.setNavigationItemSelectedListener {menuItem->
//            when(menuItem.itemId){
//
//
//            }
//        }




    }


//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        toggle.onConfigurationChanged(newConfig)
//    }



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