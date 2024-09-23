package com.example.dr_mitra.userLogin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModal(application: Application):AndroidViewModel(application){
    private val repository= AuthRepository()
    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> get() = _loginResult

    fun login(email:String,password:String){
        viewModelScope.launch {
            val result=repository.login(email,password)
            _loginResult.value=result

        }
    }
    fun getUserRole(userId: String): LiveData<String> {
        return repository.getUserRole(userId)

    }


}