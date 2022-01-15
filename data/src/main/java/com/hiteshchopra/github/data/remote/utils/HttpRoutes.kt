package com.hiteshchopra.github.data.remote.utils

object HttpRoutes {

  private const val BASE_URL = "https://api.github.com"
  fun getAllRepositoriesUrl(pageSize: Int, page: Int): String {
    return "$BASE_URL/orgs/adobe/repos?page=$page&pageSize=$pageSize"
  }

  fun getSearchReposWithQueryUrl(query: String, perPage: Int, page: Int): String {
    return "$BASE_URL/search/repositories?q=$query&per_page=$perPage&page=$page"
  }
}