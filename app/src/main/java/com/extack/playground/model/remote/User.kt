package com.extack.playground.model.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int = 0,
    val name: String = ""
) : FirestoreModel, Parcelable {
    override fun getDocId(): String {
        return "$id$name"
    }
}