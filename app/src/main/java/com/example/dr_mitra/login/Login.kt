package com.example.dr_mitra.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentLoginBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val tabTitles = arrayOf("Patient", "Doctor")
    private lateinit var imageView: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        imageView = binding.loginImg


        viewPagerWithTabLayout()
        // Inflate the layout for this fragment
        return binding.root
    }

//    override fun onPatientSignupClicked() {
//        // Navigate to the Patient Signup screen
//        findNavController().navigate(R.id.action_patientLogin_to_patientSignup)
//    }
//

//    override fun onPatientLoginButtonClicked() {
//        findNavController().navigate(R.id.action_patientLogin_to_patientHomePage)
//    }

    private fun viewPagerWithTabLayout() {
        binding.loginViewPager.adapter = LoginViewPagerAdapter(this)
        val tabLayout = binding.loginTabLayout
        val viewPager = binding.loginViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]


        }.attach()

        for (i in 0..2) {
            val textView = LayoutInflater.from(requireContext())
                .inflate(R.layout.login_tab_textview, null) as TextView
            binding.loginTabLayout.getTabAt(i)?.customView = textView
        }


        // Set a listener to change the background when a tab is selected
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val position = it.position
                    // Update background of selected tab
                    (it.customView as TextView).background = when (position) {
                        0 -> ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.login_patient_select
                        )

                        else -> ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.login_doctor_select
                        )
                    }
                    updateImageViewForTab(position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    val position = it.position
                    // Update background of unselected tab
                    (it.customView as TextView).background = when (position) {
                        0 -> ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.login_tablayout_bg
                        )

                        else -> ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.login_tablayout_bg
                        )
                    }


                    //update image according to tab change

                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun updateImageViewForTab(position: Int) {
        when (position) {
            0 -> imageView.setImageResource(R.drawable.patient_img) // Replace with your image resource

            else -> imageView.setImageResource(R.drawable.doctor_img) // Default or fallback image
        }
    }


}