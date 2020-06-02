package com.extack.playground.repo.helper

import com.extack.playground.model.Resource
import java.io.IOException
import java.net.SocketTimeoutException

class RetrofitHelper {
    inline fun <T> serviceCaller(serviceFunction: () -> T): Resource<T> {
        return try {
            Resource.SuccessSingle(serviceFunction.invoke())
        } catch (e: Exception) {
            val message = when (e) {
                is SocketTimeoutException -> "Timed Out"
                is IOException -> "Network error occured"
                else -> "Something went wrong"
            }
            Resource.Failure(message)
        }
    }
}