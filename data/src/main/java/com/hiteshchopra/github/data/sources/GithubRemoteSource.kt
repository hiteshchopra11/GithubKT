package com.hiteshchopra.github.data.sources

import com.hiteshchopra.github.data.remote.model.RepoItemData
import com.hiteshchopra.github.data.remote.service.GithubRepoService
import com.hiteshchopra.github.domain.SafeResult

class GithubRemoteSource(
  private val githubRepoService: GithubRepoService
) : IGithubRemoteSource {
  override suspend fun getPosts(pageSize: Int, page: Int): SafeResult<ArrayList<RepoItemData>> {
    return githubRepoService.getPosts(pageSize, page)
  }
}