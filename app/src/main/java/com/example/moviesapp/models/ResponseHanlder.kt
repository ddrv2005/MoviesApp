package com.example.moviesapp.models

import retrofit2.HttpException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Response<T> {
        return Response.success(data)
    }

    fun <T : Any> handleException(e: Exception): Response<T> {
        return when (e) {
            is HttpException -> Response.error(getErrorMessage(e.code()), null)
            else -> Response.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}