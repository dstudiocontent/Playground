package com.extack.playground.model.firestore

data class Rates(
    val fetchDate: String = "",
    val base: String = "",
    val rates: Map<String, Double> = mapOf()
) : FirestoreModel {
    override fun getDocId(): String {
        return fetchDate
    }
}