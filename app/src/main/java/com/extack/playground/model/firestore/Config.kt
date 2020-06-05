package com.extack.playground.model.firestore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Config(
    val updatedDate: String = ""
) : Parcelable, FirestoreModel {
    override fun getDocId(): String {
        return "config"
    }
}