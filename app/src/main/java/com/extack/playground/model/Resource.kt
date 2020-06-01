package com.extack.playground.model

sealed class Resource<out T> {
    data class SuccessSingle<out T>(val data: T) : Resource<T>()
    data class SuccessList<out T>(val data: List<T>) : Resource<T>()
    data class Failure<out T>(val message: String) : Resource<T>()
}