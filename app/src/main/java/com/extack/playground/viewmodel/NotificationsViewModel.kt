package com.extack.playground.viewmodel

import androidx.lifecycle.ViewModel
import com.extack.playground.repo.firebase.FirebaseAuthHelper

class NotificationsViewModel : ViewModel() {
    val authHelper = FirebaseAuthHelper()

    fun logout() {
        authHelper.logout()
    }
}