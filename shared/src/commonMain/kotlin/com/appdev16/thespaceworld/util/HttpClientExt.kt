package com.appdev16.thespaceworld.util

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.Network.SERIALIZATION)
    } catch (e: Exception) {
        return Result.Error(NetworkError.Network.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: SerializationException) {
                Result.Error(NetworkError.Network.SERIALIZATION)
            }
        }

        401 -> Result.Error(NetworkError.Network.UNAUTHORIZED)
        408 -> Result.Error(NetworkError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(NetworkError.Network.CONFLICT)
        413 -> Result.Error(NetworkError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(NetworkError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.Network.SERVER_ERROR)
        else -> Result.Error(NetworkError.Network.UNKNOWN)
    }
}
