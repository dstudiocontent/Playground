package com.extack.playground.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.repo.helper.UserStatus
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class SignInViewModel
@AssistedInject
constructor(
    private val authHelper: FirebaseAuthHelper,
    @Assisted
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): SignInViewModel
    }

    fun registerUser(email: String, password: String): LiveData<UserStatus> = liveData {
        emit(authHelper.loginUser(email, password))
    }
}