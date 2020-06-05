package com.extack.playground.ui.more

import androidx.lifecycle.ViewModel
import com.extack.playground.repo.helper.FirebaseAuthHelper
import javax.inject.Inject

class NotificationsViewModel
@Inject constructor(
    private val authHelper: FirebaseAuthHelper
) : ViewModel() {

    fun logout() {
        authHelper.logout()
    }
}