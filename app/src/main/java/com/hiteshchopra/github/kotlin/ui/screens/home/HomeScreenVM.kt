package com.hiteshchopra.github.kotlin.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.hiteshchopra.github.domain.usecase.FetchGithubRepositoriesUseCase
import com.hiteshchopra.github.kotlin.model.Mappers.RepoItemUIMapper
import com.hiteshchopra.github.kotlin.model.RepoItemUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeScreenVM(
  private val fetchGithubRepositoriesUseCase: FetchGithubRepositoriesUseCase,
  private val repoItemUIMapper: RepoItemUIMapper
) : ViewModel() {

  // Cached in ViewModel to survive configuration changes.

  val paginatedRepos: Flow<PagingData<RepoItemUI>> =
    fetchGithubRepositoriesUseCase.perform().map { pagingDataDomain ->
      pagingDataDomain.map { repoItemDomain ->
        repoItemUIMapper.mapToPresentation(repoItemDomain)
      }
    }.cachedIn(viewModelScope)
}