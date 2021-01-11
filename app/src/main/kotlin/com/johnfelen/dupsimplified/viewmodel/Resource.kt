package com.johnfelen.dupsimplified.viewmodel

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String?) : Resource<T>()
}

fun <T> createResource(data: T? = null, isLoading: Boolean = false): Resource<T> = when {
    isLoading -> Resource.Loading()
    data != null -> Resource.Success(data)
    else -> Resource.Error(null)
}