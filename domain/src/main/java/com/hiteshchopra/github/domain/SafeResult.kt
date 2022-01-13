package com.hiteshchopra.github.domain

sealed class SafeResult<out T> {

  data class Success<T>(val data: T) : SafeResult<T>()

  data class Failure(
    val exception: Exception? = Exception("Unknown Error"),
    val message: String = exception?.localizedMessage ?: ""
  ) : SafeResult<Nothing>()

  object NetworkError : SafeResult<Nothing>()
}