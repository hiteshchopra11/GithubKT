package com.hiteshchopra.github.data.remote.service

import com.hiteshchopra.github.data.remote.model.RepoItemData
import com.hiteshchopra.github.domain.SafeResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.ExperimentalSerializationApi

interface GithubRepoService {
  suspend fun getPosts(): SafeResult<ArrayList<RepoItemData>>

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