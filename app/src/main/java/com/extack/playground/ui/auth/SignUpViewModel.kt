package com.extack.playground.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.repo.helper.UserStatus

class SignUpViewModel : ViewModel() {

    private val authHelper =
        FirebaseAuthHelper()

    fun registerUser(email: String, password: String): LiveData<UserStatus> = liveData {
        emit(authHelper.registerUser(email, password))
    }
}