package com.reg.registrationapp.core.baseDataSource

import retrofit2.Response

abstract class BaseDataSource {
    suspend fun <T> executeRequest(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    ApiResponse.Success(responseBody)
                } else {
                    ApiResponse.Error("Empty response body")
                }
            } else {
                val errorMessage = response.errorBody()?.string()
                ApiResponse.Error(errorMessage ?: "Unknown error")
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: "Request failed")
        }
    }
}
