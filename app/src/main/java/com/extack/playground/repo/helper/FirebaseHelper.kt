package com.extack.playground.repo.helper

import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.FirestoreModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseHelper {
    private val db = FirebaseFirestore.getInstance()

    suspend fun <T : FirestoreModel> getListOfData(
        collectionPath: String,
        type: Class<T>
    ): Resource<T> {
        return try {
            Resource.SuccessList(
                db.collection(collectionPath).get().await().toObjects(type)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e.message ?: "Something went wrong")
        }
    }

    suspend fun <T : FirestoreModel> getSingleData(
        collectionPath: String,
        docId: String,
        type: Class<T>
    ): Resource<T> {
        return try {
            Resource.SuccessSingle(
                db.collection(collectionPath).document(docId).get().await().toObject(type)
                    ?: throw NullPointerException()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e.message ?: "Something went wrong")
        }
    }
}