package com.extack.playground.repo.helper

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthHelper @Inject constructor(private val auth: FirebaseAuth) {

    suspend fun registerUser(email: String, password: String): UserStatus {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await().user
            UserStatus(StatusCode.SUCCESS)
        } catch (e: Exception) {
            UserStatus(
                StatusCode.FAILED,
                e.message ?: "Something went wrong"
            )
        }
    }

    suspend fun loginUser(email: String, password: String): UserStatus {
        return try {
            auth.signInWithEmailAndPassword(email, password).await().user
            UserStatus(StatusCode.SUCCESS)
        } catch (e: Exception) {
            UserStatus(
                StatusCode.FAILED,
                e.message ?: "Something went wrong"
            )
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }
}

data class UserStatus(
    val status: StatusCode = StatusCode.STARTED,
    val message: String = ""
)

enum class StatusCode { STARTED, FAILED, SUCCESS }