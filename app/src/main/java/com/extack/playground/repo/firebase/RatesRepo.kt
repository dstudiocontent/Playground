package com.extack.playground.repo.firebase

import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.Rates
import com.extack.playground.repo.helper.FirebaseHelper
import javax.inject.Inject

class RatesRepo @Inject constructor(private val firebaseHelper: FirebaseHelper) {
    suspend fun getLatestRates(docId: String): Resource<Rates> {
        return firebaseHelper.getSingleData("rates", docId, Rates::class.java)
    }

    suspend fun saveRate(rates: Rates): Resource<Rates> {
        return firebaseHelper.saveDoc("rates", rates.getDocId(), rates)
    }
}