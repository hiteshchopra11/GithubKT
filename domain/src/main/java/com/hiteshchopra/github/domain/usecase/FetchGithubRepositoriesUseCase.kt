package com.hiteshchopra.github.domain.usecase

import androidx.paging.PagingData
import com.hiteshchopra.github.domain.model.RepoItemDomain
import com.hiteshchopra.github.domain.repo.IFetchRepositoriesRepo
import kotlinx.coroutines.flow.Flow

class FetchGithubRepositoriesUseCase(private val fetchRepositoriesRepo: IFetchRepositoriesRepo) :
  BaseUseCase<Flow<PagingData<RepoItemDomain>>, Unit> {
  override fun perform(): Flow<PagingData<RepoItemDomain>> {
    return fetchRepositoriesRepo.fetchRepositories()
  }
}