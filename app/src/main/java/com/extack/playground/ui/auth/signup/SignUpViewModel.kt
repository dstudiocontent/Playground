package com.extack.playground.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.repo.helper.UserStatus
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class SignUpViewModel
@AssistedInject
constructor(
    private val authHelper: FirebaseAuthHelper,
    @Assisted
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): SignUpViewModel
    }

    fun registerUser(email: String, password: String): LiveData<UserStatus> = liveData {
        emit(authHelper.registerUser(email, password))
    }
}