package com.extack.playground.model.remote

data class Vehicle(
    val id: Int = 0,
    val name: String = ""
) : FirestoreModel {
    override fun getDocId(): String {
        return "$id$name"
    }
}