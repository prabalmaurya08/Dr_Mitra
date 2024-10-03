package com.example.dr_mitra.mainUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
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


        //Saving Data to Patient Profile
        firestore.collection("patientProfiles").document(patientId).set(patientData)
    }


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





     fun savePatientProfileAfterLogin(
         phone: String,
         age: Int,
         sugarLevel: String,
         sleepTime: String,
         bio: String,
         gender:String
                                           //  weight: Double,
                                            // height: Double,
                                           //  bloodGroup: String,
                                           //  bloodPressure: String,
                                            // heartRate: String
                                            ):LiveData<Boolean>{
        val result = MutableLiveData<Boolean>()
        val userId = auth.currentUser?.uid ?: ""

        val patientProfileData = hashMapOf(
            "phone" to phone,
            "age" to age,
            "sugarLevel" to sugarLevel,
            "sleepTime" to sleepTime,
            "bio" to bio,
            "gender" to gender,
//            "basicInfo" to mapOf(
//                "weight" to weight,
//                "height" to height,
//                "bloodGroup" to bloodGroup
//            ),
//            "medicalDetails" to mapOf(
//                "bloodPressure" to bloodPressure,
//                "heartRate" to heartRate
//            ),
            "medicalHistory" to listOf("No serious conditions"),
            "appointments" to listOf("No appointments scheduled")
        )

        // Save Patient Profile data in Firestore under userId
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