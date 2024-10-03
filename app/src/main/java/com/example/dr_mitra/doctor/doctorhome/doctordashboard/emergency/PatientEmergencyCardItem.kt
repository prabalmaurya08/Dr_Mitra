package com.example.dr_mitra.doctor.doctorhome.doctordashboard.emergency

data class PatientEmergencyCardItem(
    val patientName: String,
    val patientDisease: String,
    val timeAgo: String,
    val location: String,
    val patientImage: Int,  // Assuming you're using drawable resource IDs for now


)