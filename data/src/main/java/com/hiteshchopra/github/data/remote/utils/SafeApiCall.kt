package com.hiteshchopra.github.data.remote.utils

import android.util.Log
import com.hiteshchopra.github.domain.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal suspend fun <T> safeApiCall(
  dispatcher: CoroutineDispatcher,
  apiCall: suspend () -> T
): SafeResult<T> {
  return withContext(dispatcher) {
    try {
      Log.e("safeApiCall", "SUCCESS")
      SafeResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
      Log.e("safeApiCall", throwable.message.toString())
      when (throwable) {
        is Exception -> SafeResult.Failure(Exception(throwable))
        else -> SafeResult.NetworkError
      }
    }
  }
}