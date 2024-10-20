package com.example.dr_mitra.patient.patientlogin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dr_mitra.mainUser.User
import com.example.dr_mitra.mainUser.UserRepository

class PatientSignUpViewModal(application: Application) : AndroidViewModel(application) {
    private val patientRepository = UserRepository()
    val signupResult: LiveData<Boolean> = MutableLiveData()




    fun signUpPatient(patientUser: User) {
        patientRepository.signUpUser(patientUser).observeForever {
            (signupResult as MutableLiveData).postValue(it)
        }
    }

}