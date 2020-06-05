package com.extack.playground.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.Rates
import com.extack.playground.model.retrofit.LatestRates
import com.extack.playground.repo.firebase.RatesRepo
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.repo.retrofit.OpenExchangeRepo
import java.time.LocalDate
import javax.inject.Inject

class DashboardViewModel
@Inject
constructor(
    private val authHelper: FirebaseAuthHelper,
    private val openExchangeRepo: OpenExchangeRepo,
    private val ratesRepo: RatesRepo
) :
    ViewModel() {

    fun isUserSignedIn(): Boolean {
        return authHelper.isUserSignedIn()
    }

    fun getRates(): LiveData<Resource<Rates>> = liveData {
        Log.w("_TAG", "WORKS")
        val latestRatesResource: Resource<LatestRates> = openExchangeRepo.getLatestRates()
        if (latestRatesResource is Resource.SuccessSingle) {
            val latestRates = latestRatesResource.data
            val rates = Rates(
                LocalDate.now().toString(),
                latestRates.base,
                latestRates.rates
            )
            emit(ratesRepo.saveRate(rates))
        } else if (
            latestRatesResource is Resource.Failure
        ) {
            emit(Resource.Failure(latestRatesResource.message))
        }
    }
}