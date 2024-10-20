package com.example.dr_mitra.userLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dr_mitra.mainUser.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun login(email: String, password: String): Result<String> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: ""

            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    fun getUserRole(userId: String): LiveData<String> {
        val userRole = MutableLiveData<String>()
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val role = documentSnapshot.getString("role") ?: ""
                    userRole.value = role
                } else {
                    userRole.postValue("User not found")
                }
            }.addOnFailureListener {
                userRole.value = ""
            }
        return userRole

    }
}




