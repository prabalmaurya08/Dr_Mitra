package com.example.dr_mitra

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import com.example.dr_mitra.databinding.ActivityMainBinding
import com.example.dr_mitra.doctor.doctorlogin.DoctorLogin
import com.example.dr_mitra.patient.patientlogin.PatientLogin

class MainActivity : AppCompatActivity(), PatientLogin.OnSignupClickListener, PatientLogin.OnLoginSuccessListener, DoctorLogin.OnSignupClickListener1 {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
    override fun onPatientSignupClicked() {
        // Navigate to Patient Signup Fragment
        findNavController(R.id.fragment).navigate(R.id.action_Login_to_patientSignup)

    }
    override fun onPatientLoginSuccess() {

        // Navigate to the Patient Home Screen
        findNavController(R.id.fragment).navigate(R.id.action_login_to_patientHomePage)
    }


    override fun onDoctorSignupClicked() {
        // Navigate to Patient Signup Fragment
        findNavController(R.id.fragment).navigate(R.id.action_doctorLogin_to_doctorSignup)

    }
}