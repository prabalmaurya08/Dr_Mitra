package com.example.dr_mitra.patient.patienthome.patientdashboard

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dr_mitra.R
import com.example.dr_mitra.databinding.FragmentPatientDashboardBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class PatientDashboard : Fragment() {
    private  lateinit var binding: FragmentPatientDashboardBinding

    private lateinit var imageSlider: ImageSlider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPatientDashboardBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

// Initialize the ImageSlider
        imageSlider = binding.imageSlider

        // Fetch and display images
        fetchImagesFromFirebase()

        return binding.root
    }
    private fun fetchImagesFromFirebase() {


        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner))
        imageSlider.setImageList(imageList,ScaleTypes.FIT)



    }


}