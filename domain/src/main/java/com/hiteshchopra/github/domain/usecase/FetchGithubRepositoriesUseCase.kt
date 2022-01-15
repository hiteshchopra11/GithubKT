package com.hiteshchopra.github.domain.usecase

import androidx.paging.PagingData
import com.hiteshchopra.github.domain.model.repo.RepoItemDomain
import com.hiteshchopra.github.domain.repo.IGithubRepo
import kotlinx.coroutines.flow.Flow

class FetchGithubRepositoriesUseCase(private val githubRepo: IGithubRepo) :
  BaseUseCase<Flow<PagingData<RepoItemDomain>>, Unit> {
  override fun perform(): Flow<PagingData<RepoItemDomain>> {
    return githubRepo.fetchRepositories()
  }
}