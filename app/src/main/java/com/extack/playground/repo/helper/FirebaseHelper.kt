package com.extack.playground.repo.helper

import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.FirestoreModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseHelper @Inject constructor(private val db: FirebaseFirestore) {

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
                    ?: throw NullPointerException("Item not found")
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e.message ?: "Something went wrong")
        }
    }

    suspend fun <T : FirestoreModel> saveDoc(
        collectionPath: String,
        docId: String,
        doc: T
    ): Resource<T> {
        return try {
            db.collection(collectionPath).document(docId).set(doc).await()
            Resource.SuccessSingle(doc)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e.message ?: "Something went wrong")
        }
    }

    suspend fun updateField(
        collectionPath: String,
        docId: String,
        field: String,
        value: String
    ): Resource<String> {
        return try {
            db.collection(collectionPath).document(docId).update(field, value).await()
            Resource.SuccessSingle("Updated Successfully")
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Something went wrong")
        }
    }
}