package com.example.dr_mitra.mainUser

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    fun signUpUser(user: User): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {




                    //this will create a unique id for the user
                    val userId = auth.currentUser?.uid ?: ""
                    val userData = hashMapOf(
                        "email" to user.email,
                        "name" to user.name,
                        "role" to user.role,

                        )

                    //now store this data in the firestore so that we can easily retrieve it

                    firestore.collection("users").document(userId).set(userData)
                        .addOnSuccessListener {
                            if (user.role == "doctor") {
                                saveDoctorProfile(userId,user)
                            }
                            else{
                                savePatientProfile(userId)

                            }

                            result.postValue(true)
                        }
                        .addOnFailureListener {
                            result.postValue(false)

                        }
                }
                else{
                    result.postValue(false)
                }
            }
        return result

    }
    private fun saveDoctorProfile(doctorId: String, user: User) {
        val doctorData = hashMapOf(

            "medicalRegistrationNumber" to user.medicalRegistrationNumber
        )
        firestore.collection("doctorProfiles").document(doctorId).set(doctorData)
    }
    private fun savePatientProfile(patientId: String) {
        val patientData = hashMapOf(
            "medicalHistory" to listOf("Nosal Problem"),
            "appointments" to listOf("No Appointment")

        )


        firestore.collection("patientProfiles").document(patientId).set(patientData)
    }
}