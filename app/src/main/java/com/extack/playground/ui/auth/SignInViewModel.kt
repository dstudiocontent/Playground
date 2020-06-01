package com.extack.playground.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.repo.firebase.FirebaseAuthHelper
import com.extack.playground.repo.firebase.UserStatus

class SignInViewModel : ViewModel() {

    private val authHelper = FirebaseAuthHelper()

    fun registerUser(email: String, password: String): LiveData<UserStatus> = liveData {
        emit(authHelper.loginUser(email, password))
    }
}