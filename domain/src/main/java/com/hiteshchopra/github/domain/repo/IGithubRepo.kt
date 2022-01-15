package com.hiteshchopra.github.domain.repo

import androidx.paging.PagingData
import com.hiteshchopra.github.domain.model.repo.RepoItemDomain
import com.hiteshchopra.github.domain.model.search.SearchResultDomainItem
import kotlinx.coroutines.flow.Flow

interface IGithubRepo {
  fun fetchRepositories(): Flow<PagingData<RepoItemDomain>>
  fun fetchSearchResults(query: String): Flow<PagingData<SearchResultDomainItem>>
}