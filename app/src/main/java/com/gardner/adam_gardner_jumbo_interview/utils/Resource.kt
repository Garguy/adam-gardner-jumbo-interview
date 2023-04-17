package com.gardner.adam_gardner_jumbo_interview.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error(val message: String?) : Resource<Nothing>()
}