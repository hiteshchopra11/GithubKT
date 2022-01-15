package com.hiteshchopra.github.data.sources

import com.hiteshchopra.github.data.remote.model.repo.RepoItemData
import com.hiteshchopra.github.data.remote.model.search.SearchResultData
import com.hiteshchopra.github.data.remote.service.GithubRepoService
import com.hiteshchopra.github.domain.SafeResult

class GithubRemoteSource(
  private val githubRepoService: GithubRepoService
) : IGithubRemoteSource {
  override suspend fun getRepos(pageSize: Int, page: Int): SafeResult<ArrayList<RepoItemData>> {
    return githubRepoService.getRepos(pageSize = pageSize, page = page)
  }

  override suspend fun getSearchResults(
    query: String,
    perPage: Int,
    page: Int
  ): SafeResult<SearchResultData> {
    return githubRepoService.getSearchResults(query = query, perPage = perPage, page = page)
  }
}