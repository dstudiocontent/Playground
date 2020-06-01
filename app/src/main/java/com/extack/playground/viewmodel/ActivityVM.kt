package com.extack.playground.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.extack.playground.model.remote.User

class ActivityVM(val savedStateHandle: SavedStateHandle) : ViewModel() {
    fun setCurrentUser(user: User) {
        savedStateHandle["USER"] = user
    }

    fun getCurrentUser(): User? = savedStateHandle["USER"]
}