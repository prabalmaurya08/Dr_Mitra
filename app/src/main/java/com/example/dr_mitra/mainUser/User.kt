package com.example.dr_mitra.mainUser

data class User(
    val email: String = "",
    val password: String = "" ,
    val name: String = "",
    val role: String = "", // Can be "doctor" or "patient"
    val medicalRegistrationNumber: String? = null, // Only for doctors


    val specialization: String? = null, // Doctor's specialization
    val experience: Int? = null, // Doctor's experience in years
    val timeSlot: String? = null, // Available time slots
    val contactNumber: String? = null, // Doctor's contact number
    val address: String? = null, // Doctor's address
    val disease: String? = null, // Doctor's district
    // Patient-specific fields
    val phone: String? = null, // Patient's phone number
    val age: Int? = null,
    val sugarLevel: String? = null,
    val sleepTime: String? = null,
    val gender: String? = null,
    val bio: String? = null, // Patient's bio
    val weight: String? = null,
    val height: String? = null,
    val bloodGroup: String? = null,
    val bloodPressure: String? = null,
    val heartRate: String? = null,
    val medicalHistory: List<String>? = null, // Medical history for patient
    val appointments: List<String>? = null // Appointment details for patient
)
