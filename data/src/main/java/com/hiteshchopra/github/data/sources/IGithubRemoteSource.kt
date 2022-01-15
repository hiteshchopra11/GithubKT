package com.hiteshchopra.github.data.sources

import com.hiteshchopra.github.data.remote.model.repo.RepoItemData
import com.hiteshchopra.github.data.remote.model.search.SearchResultData
import com.hiteshchopra.github.domain.SafeResult

interface IGithubRemoteSource {
  companion object {
    const val PAGE_SIZE_DEFAULT = 10
  }

  suspend fun getRepos(pageSize: Int, page: Int): SafeResult<ArrayList<RepoItemData>>

  suspend fun getSearchResults(query: String, perPage: Int, page: Int): SafeResult<SearchResultData>
}