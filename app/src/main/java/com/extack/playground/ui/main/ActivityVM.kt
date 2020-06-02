package com.extack.playground.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.extack.playground.model.firestore.User

class ActivityVM(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    fun setCurrentUser(user: User) {
        savedStateHandle["USER"] = user
    }

    fun getCurrentUser(): User? = savedStateHandle["USER"]

    fun setNetworkAvailable() {
        savedStateHandle["NETWORK_KEY"] = true
    }

    fun setNetworkLost() {
        savedStateHandle["NETWORK_KEY"] = false
    }

    fun isNetworkAvailable(): Boolean? = savedStateHandle["NETWORK_KEY"]
}