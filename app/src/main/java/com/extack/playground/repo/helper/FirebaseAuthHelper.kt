package com.extack.playground.repo.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseAuthHelper {
    private val auth: FirebaseAuth = Firebase.auth

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