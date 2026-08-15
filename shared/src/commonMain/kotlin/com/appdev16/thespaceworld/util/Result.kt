package com.appdev16.thespaceworld.util

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: com.appdev16.thespaceworld.util.Error>(val error: E): Result<Nothing, E>
}

inline fun <T, E: Error, R> Result<T, E>.map(transform: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(transform(data))
    }
}

fun <T, E: Error> Result<T, E>.asEmptyDataResult(): Result<Unit, E> {
    return map {  }
}
