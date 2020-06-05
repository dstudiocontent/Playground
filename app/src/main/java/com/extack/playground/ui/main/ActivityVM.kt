package com.extack.playground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.Config
import com.extack.playground.repo.firebase.ConfigRepo
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject


class ActivityVM
@AssistedInject constructor(
    private val authHelper: FirebaseAuthHelper,
    private val configRepo: ConfigRepo,
    @Assisted
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): ActivityVM
    }

    fun isUserSignedIn(): Boolean {
        return authHelper.isUserSignedIn()
    }

    fun getConfig(): LiveData<Resource<Config>> = liveData {
        emit(configRepo.getConfig())
    }

    fun setConfig(config: Config) {
        savedStateHandle["CONFIG"] = config
    }

    fun getSavedConfig(): LiveData<Config> = savedStateHandle.getLiveData("CONFIG")

    fun setNetworkAvailable() {
        savedStateHandle["NETWORK_KEY"] = true
    }

    fun setNetworkLost() {
        savedStateHandle["NETWORK_KEY"] = false
    }

    fun isNetworkAvailable(): Boolean? = savedStateHandle["NETWORK_KEY"]

}