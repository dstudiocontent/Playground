package com.extack.playground.ui.more

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.model.Resource
import com.extack.playground.model.retrofit.LatestRates
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.repo.helper.RetrofitHelper
import com.extack.playground.repo.retrofit.OpenExchangeRepo
import com.extack.playground.repo.retrofit.RetrofitClient

class NotificationsViewModel() : ViewModel() {
    private val authHelper =
        FirebaseAuthHelper()

    private val service = RetrofitClient.retrofit
    private val retrofitHelper =
        RetrofitHelper()
    private val openExchangeRepo = OpenExchangeRepo(service, retrofitHelper)

    fun getRates(): LiveData<Resource<LatestRates>> = liveData {
        emit(openExchangeRepo.getLatestRates())
    }

    fun logout() {
        authHelper.logout()
    }
}