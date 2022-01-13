package com.hiteshchopra.github.data.sources

import com.hiteshchopra.github.data.remote.service.GithubRepoService
import com.hiteshchopra.github.data.remote.model.GithubRepoItemData
import com.hiteshchopra.github.domain.SafeResult

class GithubRemoteSource(
  private val githubRepoService: GithubRepoService
) : IGithubRemoteSource {
  override suspend fun getPosts(): SafeResult<ArrayList<GithubRepoItemData>> {
    return githubRepoService.getPosts()
  }
}