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
    private val profileRepository= PatientProfileRepository()
    //for inserting
    private val _saveProfileResult = MutableLiveData<Boolean>()
    val saveProfileResult: LiveData<Boolean> get() = _saveProfileResult

    //for retrieving
    private val _patientProfile = MutableLiveData<User>()
    val patientProfile: LiveData<User> get() = _patientProfile
    //for retrieving
    private val _patientEmailName = MutableLiveData<User>()
    val patientEmailName: LiveData<User> get() = _patientEmailName




    fun savePatientProfile(
        address: String,
        phone: String,
                           age: Int,
                           sugarLevel: String,
                           sleepTime: String,
                           bio: String,
                           gender: String,
                           weight: String,
                           height: String,
                           bloodGroup: String,
                           bloodPressure: String,
                           disease: String
    ):LiveData<Boolean> {
        viewModelScope.launch {

            val resultLiveData =
                profileRepository.savePatientProfileAfterLogin(address, phone, age, sugarLevel, sleepTime, bio, gender, weight, height, bloodGroup, bloodPressure, disease)

            // Observe the LiveData from the repository and update the _saveProfileResult accordingly
            resultLiveData.observeForever { isSuccess ->
                _saveProfileResult.postValue(isSuccess)
            }

        }
        return saveProfileResult
    }

    fun getEmailName(){
        viewModelScope.launch {
            val resultLiveData = profileRepository.getEmailName()

            // Observe the LiveData from the repository and update the _patientProfile accordingly
            resultLiveData.observeForever { userEmailName ->
                _patientEmailName.postValue(userEmailName)
            }
        }
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