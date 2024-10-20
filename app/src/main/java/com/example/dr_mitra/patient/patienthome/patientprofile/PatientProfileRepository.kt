package com.example.dr_mitra.patient.patienthome.patientprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dr_mitra.mainUser.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PatientProfileRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getPatientProfile(): LiveData<User> {
        val result = MutableLiveData<User>()
        val userId = auth.currentUser?.uid ?: ""

        firestore.collection("patientProfiles").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userProfile = document.toObject(User::class.java)
                    if (userProfile != null) {
                        result.postValue(userProfile!!) // Post the user profile
                    } else {
                        result.postValue(User()) // Return a default User object if null
                    }
                } else {
                    result.postValue(User()) // Return a default User object if document does not exist
                }
            }
            .addOnFailureListener {
                result.postValue(User()) // Return a default User object on failure
            }

        return result
    }
    fun getEmailName(): LiveData<User> {
        val emailName = MutableLiveData<User>()
        val userId = auth.currentUser?.uid ?: ""
        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userProfile = document.toObject(User::class.java)
                    if (userProfile != null) {
                        emailName.postValue(userProfile!!) // Post the user profile
                    } else {
                        emailName.postValue(User()) // Return a default User object if null
                    }
                } else {
                    emailName.postValue(User()) // Return a default User object if document does not exist
                }
            }
            .addOnFailureListener {
                emailName.postValue(User()) // Return a default User object on failure
            }
        return emailName
    }


    fun savePatientProfileAfterLogin(
        address: String,
        phone: String,
        age: Int,
        sugarLevel: String,
        sleepTime: String,
        bio: String,
        gender:String,

          weight: String,
         height: String,
          bloodGroup: String,
          bloodPressure: String,
        disease: String

    ):LiveData<Boolean>{
        val result = MutableLiveData<Boolean>()
        val userId = auth.currentUser?.uid ?: ""

        val patientProfileData = hashMapOf(

            "address" to address,
            "phone" to phone,
            "age" to age,
            "sugarLevel" to sugarLevel,
            "sleepTime" to sleepTime,
            "bio" to bio,
            "gender" to gender,

                "weight" to weight,
                "height" to height,




                "bloodGroup" to bloodGroup,
                "disease" to disease,
                "bloodPressure" to bloodPressure,

            "medicalHistory" to listOf("No serious conditions"),
            "appointments" to listOf("No appointments scheduled")
        )

        // Save Patient Profile data in FireStore under userId
        firestore.collection("patientProfiles").document(userId).set(patientProfileData)
            .addOnSuccessListener {
                result.postValue(true)
            }
            .addOnFailureListener {
                result.postValue(false)
            }

        return result


    }
}