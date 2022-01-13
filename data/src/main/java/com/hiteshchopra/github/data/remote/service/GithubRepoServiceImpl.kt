package com.hiteshchopra.github.data.remote.service

import com.hiteshchopra.github.data.remote.utils.HttpRoutes
import com.hiteshchopra.github.data.remote.model.GithubRepoItemData
import com.hiteshchopra.github.data.remote.utils.safeApiCall
import com.hiteshchopra.github.domain.SafeResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GithubRepoServiceImpl(
  private val client: HttpClient,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) : GithubRepoService {
  override suspend fun getPosts(): SafeResult<ArrayList<GithubRepoItemData>> {
    return safeApiCall(dispatcher){
      client.get { url(HttpRoutes.REPOSITORIES) }
    }
  }
}