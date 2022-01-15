package com.hiteshchopra.github.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hiteshchopra.github.data.remote.model.repo.RepoItemDomainMapper
import com.hiteshchopra.github.data.remote.model.search.SearchResultItemEntityMapper
import com.hiteshchopra.github.data.sources.IGithubRemoteSource
import com.hiteshchopra.github.data.sources.RepoPagerSource
import com.hiteshchopra.github.data.sources.SearchPagerSource
import com.hiteshchopra.github.domain.model.repo.RepoItemDomain
import com.hiteshchopra.github.domain.model.search.SearchResultDomainItem
import com.hiteshchopra.github.domain.repo.IGithubRepo
import kotlinx.coroutines.flow.Flow

class GithubRepo(
  private val githubRemoteSource: IGithubRemoteSource,
  private val repoItemDomainMapper: RepoItemDomainMapper,
  private val searchResultItemEntityMapper: SearchResultItemEntityMapper
) : IGithubRepo {
  override fun fetchRepositories(): Flow<PagingData<RepoItemDomain>> {
    return Pager(
      PagingConfig(pageSize = 10)
    ) {
      RepoPagerSource(githubRemoteSource, repoItemDomainMapper)
    }.flow
  }

  override fun fetchSearchResults(query: String): Flow<PagingData<SearchResultDomainItem>> {
    return Pager(
      PagingConfig(pageSize = 10)
    ) {
      SearchPagerSource(
        query = query,
        githubRemoteSource = githubRemoteSource,
        searchResultItemEntityMapper = searchResultItemEntityMapper
      )
    }.flow
  }
}