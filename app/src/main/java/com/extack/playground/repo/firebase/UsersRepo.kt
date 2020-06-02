package com.extack.playground.repo.firebase

import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.User
import com.extack.playground.model.firestore.Vehicle
import com.extack.playground.repo.helper.FirebaseHelper

class UsersRepo(private val firebaseHelper: FirebaseHelper) {
    suspend fun getAllUsers(): Resource<User> {
        return firebaseHelper.getListOfData("users", User::class.java)
    }

    suspend fun getUser(docId: String): Resource<User> {
        return firebaseHelper.getSingleData("users", docId, User::class.java)
    }

    suspend fun getVehiclesByUser(userId: Int, userName: String): Resource<Vehicle> {
        return firebaseHelper.getListOfData(
            "users/${userId}${userName}/vehicles",
            Vehicle::class.java
        )
    }
}