package com.hiteshchopra.github.data.remote.utils

object HttpRoutes {

  private const val BASE_URL = "https://api.github.com"
  fun getRepositoriesUrl(pageSize: Int, page: Int): String {
    return "$BASE_URL/orgs/adobe/repos?page=$page&pageSize=$pageSize"
  }

}