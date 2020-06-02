package com.extack.playground.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.extack.playground.repo.helper.FirebaseAuthHelper

class DashboardVMFactory(
    private val authHelper: FirebaseAuthHelper,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(
    owner, defaultArgs
) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        @Suppress("UNCHECKED_CAST")
        return DashboardViewModel(authHelper) as T
    }

}