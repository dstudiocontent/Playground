package com.extack.playground.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.User
import com.extack.playground.repo.firebase.UsersRepo
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.repo.helper.FirebaseHelper

class HomeViewModel : ViewModel() {
    private val userRepo = UsersRepo(FirebaseHelper())
    private val authHelper =
        FirebaseAuthHelper()

    fun getAllUsers(): LiveData<Resource<User>> = liveData {
        emit(userRepo.getAllUsers())
    }

    fun isUserLoggedIn(): Boolean {
        return authHelper.isUserSignedIn()
    }


}