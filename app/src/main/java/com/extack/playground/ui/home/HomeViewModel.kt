package com.extack.playground.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.Rates
import com.extack.playground.repo.firebase.RatesRepo
import com.extack.playground.repo.helper.FirebaseAuthHelper
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val ratesRepo: RatesRepo,
    private val authHelper: FirebaseAuthHelper
) : ViewModel() {

    fun getLatestRates(docId: String): LiveData<Resource<Rates>> = liveData {
        emit(ratesRepo.getLatestRates(docId))
    }

    fun isUserLoggedIn(): Boolean {
        return authHelper.isUserSignedIn()
    }


}