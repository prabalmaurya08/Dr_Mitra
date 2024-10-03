package com.example.dr_mitra.patient.patienthome.patientprofile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dr_mitra.mainUser.User
import com.example.dr_mitra.mainUser.UserRepository
import kotlinx.coroutines.launch

class PatientProfileViewModal(application: Application) : AndroidViewModel(application) {
    private val profileRepository= UserRepository()
    //for inserting
    private val _saveProfileResult = MutableLiveData<Boolean>()
    val saveProfileResult: LiveData<Boolean> get() = _saveProfileResult

    //for retrieving
    private val _patientProfile = MutableLiveData<User>()
    val patientProfile: LiveData<User> get() = _patientProfile




    fun savePatientProfile(phone: String,
                           age: Int,
                           sugarLevel: String,
                           sleepTime: String,
                           bio: String,
                           gender: String,
                           //weight: Double,
                           //height: Double,
    ):LiveData<Boolean> {
        viewModelScope.launch {

            val resultLiveData =
                profileRepository.savePatientProfileAfterLogin(phone, age, sugarLevel, sleepTime,gender, bio)

            // Observe the LiveData from the repository and update the _saveProfileResult accordingly
            resultLiveData.observeForever { isSuccess ->
                _saveProfileResult.postValue(isSuccess)
            }

        }
        return saveProfileResult
    }



    fun getPatientProfile() {
        viewModelScope.launch {
            val resultLiveData = profileRepository.getPatientProfile()

            // Observe the LiveData from the repository and update the _patientProfile accordingly
            resultLiveData.observeForever { profile ->
                _patientProfile.postValue(profile)
            }
        }
    }


}