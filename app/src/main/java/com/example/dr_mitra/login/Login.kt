package com.example.dr_mitra.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentLoginBinding
import com.google.android.material.tabs.TabLayoutMediator


class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding
private val tabTitles = arrayOf("Patient", "Doctor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewPagerWithTabLayout()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun viewPagerWithTabLayout() {
       binding.loginViewPager.adapter=LoginViewPagerAdapter(this)
        val tabLayout=binding.loginTabLayout
        val viewPager=binding.loginViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]


        }.attach()

        for(i in 0..2){
            val textView=LayoutInflater.from(requireContext()).inflate(R.layout.login_tab_textview,null) as TextView
            binding.loginTabLayout.getTabAt(i)?.customView=textView
        }
    }
}