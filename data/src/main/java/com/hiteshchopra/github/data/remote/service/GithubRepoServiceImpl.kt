package com.hiteshchopra.github.data.remote.service

import com.hiteshchopra.github.data.BuildConfig
import com.hiteshchopra.github.data.remote.model.RepoItemData
import com.hiteshchopra.github.data.remote.utils.HttpRoutes
import com.hiteshchopra.github.data.remote.utils.safeApiCall
import com.hiteshchopra.github.domain.SafeResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GithubRepoServiceImpl(
  private val client: HttpClient,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) : GithubRepoService {
  override suspend fun getPosts(pageSize: Int, page: Int): SafeResult<ArrayList<RepoItemData>> {
    return safeApiCall(dispatcher) {
      client.get {
        /**
         * Remove the header if you want to run the API without
         * authentication/token. Note that the rate limit is
         * 60 API hits per hour for unauthenticated users.
         */
        headers {
          append(HttpHeaders.Authorization, "token ${BuildConfig.ACCESS_TOKEN}")
        }
        url(HttpRoutes.getRepositoriesUrl(pageSize, page))
      }
    }
  }
}