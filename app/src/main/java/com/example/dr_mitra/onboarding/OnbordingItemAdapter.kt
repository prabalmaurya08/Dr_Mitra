package com.example.dr_mitra.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnbordingItemAdapter (list: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm,lifecycle) {

    private val fragmentList = list

    override fun getItemCount(): Int {
        return fragmentList.size

    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}