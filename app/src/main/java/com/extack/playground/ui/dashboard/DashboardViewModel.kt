package com.extack.playground.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import com.extack.playground.repo.helper.FirebaseAuthHelper

class DashboardViewModel(private val authHelper: FirebaseAuthHelper) :
    ViewModel() {
    fun pr() {
        Log.w("_TAG_VM", authHelper.isUserSignedIn().toString())
    }
}