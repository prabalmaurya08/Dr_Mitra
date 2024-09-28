package com.example.dr_mitra.doctor.doctorlogin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dr_mitra.mainUser.User
import com.example.dr_mitra.mainUser.UserRepository

class DoctorSignUpViewModal(application: Application) : AndroidViewModel(application) {
    private val doctorRepository = UserRepository()
    val signupResult: LiveData<Boolean> = MutableLiveData()
    fun signUpDoctor(doctorUser: User) {
        doctorRepository.signUpUser(doctorUser).observeForever {
            (signupResult as MutableLiveData).postValue(it)

        }
    }



}