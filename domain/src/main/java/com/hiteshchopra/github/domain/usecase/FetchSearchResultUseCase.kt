package com.hiteshchopra.github.domain.usecase

import androidx.paging.PagingData
import com.hiteshchopra.github.domain.model.search.SearchResultDomainItem
import com.hiteshchopra.github.domain.repo.IGithubRepo
import kotlinx.coroutines.flow.Flow

class FetchSearchResultUseCase(private val githubRepo: IGithubRepo) :
  BaseUseCase<Flow<PagingData<SearchResultDomainItem>>, String> {
  override fun perform(params: String): Flow<PagingData<SearchResultDomainItem>> {
    return githubRepo.fetchSearchResults(query = params)
  }
}