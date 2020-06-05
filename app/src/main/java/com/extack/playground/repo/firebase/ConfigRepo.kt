package com.extack.playground.repo.firebase

import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.Config
import com.extack.playground.repo.helper.FirebaseHelper
import javax.inject.Inject

class ConfigRepo @Inject constructor(private val firebaseHelper: FirebaseHelper) {
    suspend fun getConfig(): Resource<Config> {
        return firebaseHelper.getSingleData("settings", "config", Config::class.java)
    }
}