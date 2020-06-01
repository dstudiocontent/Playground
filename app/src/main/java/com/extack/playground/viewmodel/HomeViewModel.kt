package com.extack.playground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.model.Resource
import com.extack.playground.model.remote.User
import com.extack.playground.repo.firebase.FirebaseAuthHelper
import com.extack.playground.repo.firebase.FirebaseHelper
import com.extack.playground.repo.firebase.UsersRepo

class HomeViewModel : ViewModel() {
    private val userRepo = UsersRepo(FirebaseHelper())
    private val authHelper = FirebaseAuthHelper()

    fun getAllUsers(): LiveData<Resource<User>> = liveData {
        emit(userRepo.getAllUsers())
    }

    fun isUserLoggedIn(): Boolean {
        return authHelper.isUserSignedIn()
    }


}