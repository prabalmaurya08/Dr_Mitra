package com.example.dr_mitra.mainUser

data class User(
    val email: String = "",
    val password: String = "" ,
    val name: String = "",
    val role: String = "", // Can be "doctor" or "patient"
    val medicalRegistrationNumber: String? = null // Only for doctors
)
