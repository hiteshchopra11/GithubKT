package com.hiteshchopra.github.data.remote.service

import com.hiteshchopra.github.data.remote.model.repo.RepoItemData
import com.hiteshchopra.github.data.remote.model.search.SearchResultData
import com.hiteshchopra.github.domain.SafeResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import kotlinx.serialization.ExperimentalSerializationApi

interface GithubRepoService {

  suspend fun getRepos(pageSize: Int, page: Int): SafeResult<ArrayList<RepoItemData>>

  suspend fun getSearchResults(query: String, perPage: Int, page: Int): SafeResult<SearchResultData>

  companion object {
    @ExperimentalSerializationApi fun create(): GithubRepoService {
      return GithubRepoServiceImpl(
        client = HttpClient(Android) {
          install(JsonFeature) {
            serializer = GsonSerializer() {
              setPrettyPrinting()
              disableHtmlEscaping()
            }
          }
        }
      )
    }
  }
}