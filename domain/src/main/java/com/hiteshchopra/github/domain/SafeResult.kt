package com.hiteshchopra.github.domain

import com.hiteshchopra.github.domain.SafeResult.Failure
import com.hiteshchopra.github.domain.SafeResult.Success

sealed class SafeResult<out T> {

  data class Success<T>(val data: T) : SafeResult<T>()

  data class Failure(
    val exception: Exception? = Exception("Unknown Error"),
    val message: String = exception?.localizedMessage ?: ""
  ) : SafeResult<Nothing>()

  object NetworkError : SafeResult<Nothing>()
}

fun <T> SafeResult<T>.getSuccessOrNull(): T? {
  return when (this) {
    is Success -> this.data
    else -> null
  }
}

fun <T> SafeResult<T>.getErrorOrNull(): Failure? {
  return when (this) {
    is Failure -> this
    else -> null
  }
}