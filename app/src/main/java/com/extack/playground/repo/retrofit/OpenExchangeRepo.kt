package com.extack.playground.repo.retrofit

import com.extack.playground.constants.APP_ID
import com.extack.playground.model.Resource
import com.extack.playground.model.retrofit.LatestRates
import com.extack.playground.repo.helper.RetrofitHelper
import javax.inject.Inject

class OpenExchangeRepo @Inject constructor(
    private val service: OpenExchangeService,
    private val helper: RetrofitHelper
) {
    suspend fun getLatestRates(): Resource<LatestRates> {
        return helper.serviceCaller { service.getLatestCurrencies(APP_ID) }
    }

}