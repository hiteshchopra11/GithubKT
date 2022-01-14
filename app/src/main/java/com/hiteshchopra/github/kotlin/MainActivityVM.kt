package com.hiteshchopra.github.kotlin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.hiteshchopra.github.domain.SafeResult
import com.hiteshchopra.github.domain.model.RepoItemDomain
import com.hiteshchopra.github.domain.usecase.FetchGithubRepositoriesUseCase
import com.hiteshchopra.github.kotlin.model.RepoItemUI
import com.hiteshchopra.github.kotlin.model.RepoItemUIMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivityVM(
  private val fetchGithubRepositoriesUseCase: FetchGithubRepositoriesUseCase,
  private val repoItemUIMapper: RepoItemUIMapper
) : ViewModel() {

  fun getPaginatedImages(): Flow<PagingData<RepoItemUI>> {
    return fetchGithubRepositoriesUseCase.perform().map { pagingDataDomain ->
      pagingDataDomain.map { repoItemDomain ->
        repoItemUIMapper.mapToPresentation(repoItemDomain)
      }
    }
  }
}